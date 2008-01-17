/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 Adempiere, Inc. All Rights Reserved.                    *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.compiere.grid;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.compiere.model.StateChangeEvent;
import org.compiere.model.StateChangeListener;
import org.compiere.model.GridTab;
import org.compiere.model.MRole;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class GridSynchronizer implements PropertyChangeListener, StateChangeListener {
	
	private GridController parent;
	private GridController child;

	/**
	 * 
	 * @param parent
	 * @param child
	 */
	public GridSynchronizer(GridController parent, GridController child) {
		this.parent = parent;
		this.child = child;
	
		parent.getMTab().addPropertyChangeListener(this);
		parent.getMTab().addStateChangeListener(this);
	}

	/**
	 * @param evt
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(GridTab.PROPERTY)) {
			if (child.isCurrent()) {
				child.getMTab().dataRefresh();
			} else {
				MRole role = MRole.getDefault(); 
				child.query (false, 0, role.getMaxQueryRecords());
			}
		}
	}

	/**
	 * @param event
	 */
	public void stateChange(StateChangeEvent event) {
		if (child.isCurrent()) {
			child.getMTab().dataRefresh();
		} else {
			MRole role = MRole.getDefault(); 
			child.query (false, 0, role.getMaxQueryRecords());
		}
	}

}