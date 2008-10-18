/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * Created on Aug 1, 2005 by din
 */

package org.posterita.beans;

import java.sql.Timestamp;

public class MessageBean extends UDIBean
{
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	public Integer getMessageID()
	{
		return messageID;
	}
	public void setMessageID(Integer messageID)
	{
		this.messageID = messageID;
	}
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	public Integer getCounterOrgID()
	{
		return counterOrgID;
	}
	public void setCounterOrgID(Integer counterOrgID)
	{
		this.counterOrgID = counterOrgID;
	}
	public String getCounterOrgName()
	{
		return counterOrgName;
	}
	public void setCounterOrgName(String counterOrgName)
	{
		this.counterOrgName = counterOrgName;
	}
	public Integer getOrgID()
	{
		return OrgID;
	}
	public void setOrgID(Integer orgID)
	{
		OrgID = orgID;
	}
	public String getRead()
	{
		return read;
	}
	public void setRead(String read)
	{
		this.read = read;
	}
	public Timestamp getDateCreated()
	{
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated)
	{
		this.dateCreated = dateCreated;
	}
	public String getCounterOrderStatusInfo()
	{
		return counterOrderStatusInfo;
	}
	public void setCounterOrderStatusInfo(String counterOrderStatusInfo)
	{
		this.counterOrderStatusInfo = counterOrderStatusInfo;
	}
	public String getOrderStatusInfo()
	{
		return orderStatusInfo;
	}
	public void setOrderStatusInfo(String orderStatusInfo)
	{
		this.orderStatusInfo = orderStatusInfo;
	}
	public Boolean getIsActive()
	{
		return isActive;
	}
	public void setIsActive(Boolean isActive)
	{
		this.isActive = isActive;
	}
	
    public Integer[] getCheckBox() 
    {
        return checkBox;
    }
    
    public void setCheckBox(Integer[] checkBox) 
    {
        this.checkBox = checkBox;
    }
}