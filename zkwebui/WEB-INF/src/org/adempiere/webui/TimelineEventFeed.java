package org.adempiere.webui;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adempiere.webui.component.ZkCssHelper;
import org.adempiere.webui.session.SessionContextListener;
import org.adempiere.webui.session.WebContext;
import org.compiere.model.MAssignmentSlot;
import org.compiere.model.ScheduleUtil;
import org.compiere.util.Env;
import org.zkforge.timeline.util.TimelineUtil;
import org.zkoss.web.fn.XMLFns;
import org.zkoss.xml.XMLs;

public class TimelineEventFeed extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		WebContext ctx = (WebContext)req.getSession().getAttribute(SessionContextListener.SESSION_CTX);
        if (ctx == null) {
             return;
        } else {
        	WebContext.setCurrentInstance(ctx);
        }
         
		int resourceId  = 0;
		String resourceIdParam = req.getParameter("S_Resource_ID");
		if (resourceIdParam != null && resourceIdParam.trim().length() > 0) {
			try {
				resourceId = Integer.parseInt(resourceIdParam.trim());
			} catch (Exception e) {
				return;
			}
		} else {
			return;
		}
		
		String uuid = req.getParameter("uuid");
		if (uuid == null || uuid.trim().length() == 0) return;
		
		Date date = null;
		String dateParam = req.getParameter("date");
		if (dateParam != null && dateParam.trim().length() > 0) {
			try {
				date = DateFormat.getInstance().parse(dateParam);
			} catch (ParseException e) {
				return;
			}
		} else {
			return;
		}

		resp.setContentType("application/xml");
		ScheduleUtil m_model = new ScheduleUtil (Env.getCtx());
		
		//		Calculate Start Day
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Timestamp startDate = new Timestamp(cal.getTimeInMillis());
		//	Calculate End Date
		cal.add(Calendar.MONTH, 1);
		Timestamp endDate = new Timestamp (cal.getTimeInMillis());
		
		MAssignmentSlot[] mas = m_model.getAssignmentSlots (resourceId, startDate, endDate, null, true, null);
		if (mas == null || mas.length == 0) return;
		
		StringBuffer xml = new StringBuffer();
		xml.append("<data>").append("\r\n");
		
		for (MAssignmentSlot slot : mas) {
			xml.append("<event ").append("\r\n");
			xml.append(XMLFns.attr("start", TimelineUtil.formatDateTime(new Date(slot.getStartTime().getTime()))));
			if (slot.getEndTime() != null) {
				xml.append("\r\n");
				xml.append(XMLFns.attr("end", TimelineUtil.formatDateTime(new Date(slot.getEndTime().getTime()))));
				xml.append("\r\n");				
				xml.append(XMLFns.attr("isDuration", "true"));
			}
			xml.append(XMLFns.attr("color", "#"+ZkCssHelper.createHexColorString(slot.getColor(true))));
			xml.append("\r\n")
			   .append(XMLFns.attr("title", slot.getName()))
			   .append("\r\n")
			   .append(">");
			if (slot.getDescription() != null && slot.getDescription().trim().length() > 0) {
				xml.append("\r\n")
				   .append(XMLs.encodeText(slot.getDescription()))
				   .append("<br/>");
			}
			if (slot.getMAssignment() != null) {
				//encode assignment id as coordinate x
				String link = "<a href=\"javascript:void(0)\" onclick=\""
				    + "zkau.send({uuid: '" + uuid + "', cmd: 'onClick', data: " 
				    + "[" + slot.getMAssignment().getS_ResourceAssignment_ID() + ", 0]" 
				    + ", ctl: true})\">Edit</a>";
				xml.append("\r\n").append(XMLs.encodeText(link));
			}
			xml.append("\r\n").append("</event>").append("\r\n");
		}
		
		xml.append("</data>").append("\r\n");
		
		PrintWriter writer = resp.getWriter();
		BufferedWriter buffer = new BufferedWriter(writer);
		buffer.write(xml.toString());
		buffer.flush();
	}	
}