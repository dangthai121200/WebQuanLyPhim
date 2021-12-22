<%-- 
    Document   : rdFilmType
    Created on : Nov 11, 2021, 10:38:37 PM
    Author     : gaone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<label
    class="inline-flex items-center text-gray-600 dark:text-gray-400"
    >
    <input required=""
        type="radio"
        class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
        name="filmType"
        value="${param.idFilmType}"
        />
    <span class="ml-2">${param.nameFilmType}</span>
</label>

