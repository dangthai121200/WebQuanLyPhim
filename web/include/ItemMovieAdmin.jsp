<%-- 
    Document   : itemmovieadmin
    Created on : Nov 11, 2021, 4:44:58 PM
    Author     : gaone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<tr class="text-gray-700 dark:text-gray-400">
    <td class="px-4 py-3 text-sm">
        ${param.id}
    </td>
    <td class="px-4 py-3 text-sm">
        ${param.nameAdmin}
    </td>
    <td class="px-4 py-3 text-sm">
        ${param.nameMovie}
    </td>
    <td class="px-4 py-3 text-sm">
         ${param.title}
    </td>
    <td class="px-4 py-3 text-sm">
         ${param.dateAction}
    </td>
</tr>
