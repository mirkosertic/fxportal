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
