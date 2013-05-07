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

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MicroDataExtractor {

    public MicroData extractMicroDataFrom(Document aDocument) {
        MicroData theData = new MicroData(aDocument);

        processNodeList(aDocument, theData);

        return theData;
    }

    private void processNodeList(Node aNode, MicroDataElement aElement) {

        MicroDataElement theElement = aElement;

        NamedNodeMap theAttributes = aNode.getAttributes();
        if (theAttributes != null) {
            Node theItemScope = theAttributes.getNamedItem("itemscope");
            if (theItemScope != null) {
                String theItemType = theAttributes.getNamedItem("itemtype").getTextContent();

                MicroDataElement theNewElement = new MicroDataElement(theItemType, aNode);
                aElement.append(theNewElement);

                theElement = theNewElement;
            } else {
                Node theItemProp = theAttributes.getNamedItem("itemprop");
                if (theItemProp != null) {
                    MicroDataElement theNewElement = new MicroDataElement(theElement.getItemType(), theItemProp.getTextContent(), aNode);
                    aElement.append(theNewElement);
                }
            }
        }

        NodeList theChilds = aNode.getChildNodes();
        for (int i = 0; i < theChilds.getLength(); i++) {
            Node theChild = theChilds.item(i);
            processNodeList(theChild, theElement);
        }
    }
}
