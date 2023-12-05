<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
List of likes:
<table border="1">
    <thead>
    <td>ID#</td>
    <td>Name</td>
    <td>Photo</td>
    </thead>
    <tbody>
    <#list rows as row>
        <tr>
            <td>
                ${row.profile_id()}
            </td>
            <td>
                <a href="/messages/${row.profile_id()}" target="_blank">${row.profileName()}</a>
            </td>
            <td><img src="${row.url()}"  width="100" height="125"></td>

        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
