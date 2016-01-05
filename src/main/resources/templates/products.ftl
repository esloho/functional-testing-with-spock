<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/demo.css" media="screen, projection" rel="stylesheet" type="text/css"/>
</head>
<body>

<h1>Product List</h1>

<div>
    <#--<p class="label">-->
        <#--<span>Name</span>-->
        <#--<span>Available</span>-->
    <#--</p>-->

    <#list products>
        <table>
            <thead>
                <tr>
                    <th><span class="label">Name</span></th>
                    <th><span class="label">Category</span></th>
                    <th><span class="label">Available</span></th>
                </tr>
            </thead>
            <tbody>

            <#items as product>
                <tr>
                    <td><span class="name">${product.name}</span></td>
                    <td><span class="category">${product.category}</span></td>
                    <td><span class="amount">${product.amount}</span></td>
                </tr>
            </#items>

            </tbody>
        </table>
    <#else>
        <p><span>There are no available products at this moment</span></p>
    </#list>
</div>

<form action="/new" method="GET">
    <input type="submit" id="new-link" value="New product">
</form>

</body>
</html>