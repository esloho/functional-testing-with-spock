<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<h1>Product List</h1>

<div>
    <span>Name</span>
    <span>Available</span>
</div>

<#list products as product>
    <div>
        <span class="name">${product.name}</span>
        <span class="amount">${product.amount}</span>
    </div>
<#else>
    <div><span>No available products</span></div>
</#list>

<a class="link" href="/hello">Hello</a>

</body>
</html>