<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/demo.css" media="screen, projection" rel="stylesheet" type="text/css"/>
</head>
<body>

<h1>New Product</h1>

<form class="product-form" action="/new" method="POST" accept-charset="UTF-8">
    <div>
        <@spring.bind "productForm.name" />
        <#if spring.status.errorMessages?size gt 0 >
            <p class="error">
                <span>
                    This field <@spring.showErrors separator=". " />
                </span>
            </p>
        </#if>
        <p>
            <span class="label">Name: </span>
            <@spring.formInput path="productForm.name" attributes="placeholder=\"Product name\" value=${productForm.name}" />
        </p>
    </div>

    <div>
        <@spring.bind "productForm.category" />
        <#if spring.status.errorMessages?size gt 0 >
            <p class="error">
                <span>
                    This field <@spring.showErrors separator=". " />
                </span>
            </p>
        </#if>

        <p>
            <span class="label">Category: </span>
            <#assign options = {"": " ", "1": "Book", "2": "Album", "3": "Movie" } />
            <@spring.formSingleSelect "productForm.category", options, "" />
        </p>
    </div>

    <div>
        <@spring.bind "productForm.amount" />
        <#if spring.status.errorMessages?size gt 0 >
            <p class="error">
                <span>
                    This field <@spring.showErrors separator=". " />
                </span>
            </p>
        </#if>
        <p>
            <span class="label">Available units: </span>
            <@spring.formInput path="productForm.amount" attributes="value=${productForm.amount}" />
        </p>
    </div>

    <div class="update">
        <p>
            <input type="button" value="Submit" id="save-button" />
            <input type="button" value="Cancel" id="cancel-button" />
            <input type="reset" value="Reset" id="reset-button" />
        </p>
    </div>
</form>

<script type="text/javascript" src="//code.jquery.com/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/product-form.js"></script>

</body>
</html>