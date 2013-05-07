/**
 * FXPortal - An example application to show JavaFX portal capabilities
 * Copyright (C) 2013 Mirko Sertic
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>.
 */
package de.mirkosertic.portalprototype;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class MicroDataElement {

    private Node node;

    private String itemType;

    private String itemProp;

    private List<MicroDataElement> childs;

    public MicroDataElement(String itemType, Node aNode) {
        this.itemType = itemType;
        this.node = aNode;
        childs = new ArrayList<MicroDataElement>();
    }

    public MicroDataElement(String itemType, String itemProp, Node aNode) {
        this(itemType, aNode);
        this.itemProp = itemProp;
    }

    public void append(MicroDataElement aElement) {
        childs.add(aElement);
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemProp() {
        return itemProp;
    }

    public List<MicroDataElement> items(String aItemType) {
        List<MicroDataElement> theResult = new ArrayList<MicroDataElement>();
        if (aItemType.equals(itemType)) {
            theResult.add(this);
        }
        for (MicroDataElement theChild : childs) {
            theResult.addAll(theChild.items(aItemType));
        }
        return theResult;
    }

    public String textValue() {
        return node.getTextContent();
    }

    public Node getNode() {
        return node;
    }
}
