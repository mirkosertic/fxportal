<!DOCTYPE html>
<html>
    <head>
       <link rel="stylesheet" href="../uistyle.css"/>
    </head>
    <body>
        <h1>List of invoices</h1>

        <table>
            <tr>
                <th>Invoice number</th>
                <th>Customer number</th>
                <th>Product number</th>
                <th>Price</th>
            </tr>
            <tr>
                <td><a href="invoices/INV1">00001</a></td>
                <td><span itemscope itemtype="http://mirkosertic.de/fxportal/customerid">00001</span></a></td>
                <td><span itemscope itemtype="http://mirkosertic.de/fxportal/productid">SDK160</span></td>
                <td>10 EUR</td>
            </tr>
            <tr>
                <td><a href="invoices/INV2">00001</a></td>
                <td><span itemscope itemtype="http://mirkosertic.de/fxportal/customerid">00001</span></a></td>
                <td><span itemscope itemtype="http://mirkosertic.de/fxportal/productid">SDK170</span></td>
                <td>10 EUR</td>
            </tr>
        </table>
    </body>
</html>