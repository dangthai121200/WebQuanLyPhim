<%-- 
    Document   : ListCheckBoxCategory
    Created on : Nov 11, 2021, 10:39:43 PM
    Author     : gaone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<label
    class="inline-flex items-center text-gray-600 dark:text-gray-400"
    >
    <input
        type="checkbox"
        class="text-purple-600 form-check focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
        name="category"
        value="${param.idCategory}"
        />
    <span class="ml-2">${param.nameCategory}</span>
</label>