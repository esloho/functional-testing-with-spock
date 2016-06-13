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
        <table>
            <thead>
                <tr>
                    <th><span class="label">Name</span></th>
                    <th><span class="label">Category</span></th>
                    <th><span class="label">Available</span></th>
                </tr>
            </thead>
            <tbody>

            <#list products as product>
                <tr>
                    <td><span class="name">${product.name}</span></td>
                    <td><span class="category">${product.category}</span></td>
                    <td><span class="amount">${product.amount}</span></td>
                </tr>
            <#else>
                <p><span>There are no available products at this moment</span></p>
            </#list>
            </tbody>
        </table>
</div>

<form action="/new" method="GET">
    <input type="submit" id="new-product" value="New product">
</form>

</body>
</html>