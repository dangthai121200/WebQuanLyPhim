
<%@page import="model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<% Admin admin = (Admin) request.getSession().getAttribute("admin"); %>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
    <head>
        <c:import url="include/header.jsp"/>
    </head>
    <body>
        <div
            class="flex h-screen bg-gray-50 dark:bg-gray-900"
            :class="{ 'overflow-hidden': isSideMenuOpen}"
            >
            <!-- Desktop sidebar -->
            <c:import url="include/navbarleft.jsp">
                <c:param value=' <span
                         class="absolute inset-y-0 left-0 w-1 bg-purple-600 rounded-tr-lg rounded-br-lg"
                         aria-hidden="true"
                         ></span>' name="profile"/>
            </c:import>
            <!-- Mobile sidebar -->
            <!-- Backdrop -->
            <div
                x-show="isSideMenuOpen"
                x-transition:enter="transition ease-in-out duration-150"
                x-transition:enter-start="opacity-0"
                x-transition:enter-end="opacity-100"
                x-transition:leave="transition ease-in-out duration-150"
                x-transition:leave-start="opacity-100"
                x-transition:leave-end="opacity-0"
                class="fixed inset-0 z-10 flex items-end bg-black bg-opacity-50 sm:items-center sm:justify-center"
                ></div>

            <div class="flex flex-col flex-1">
                <c:import url="include/navbartop.jsp"></c:import>
                    <main class="h-full pb-16 overflow-y-auto">
                        <div class="container px-6 mx-auto grid">
                            <h2
                                class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200"
                                >
                                Thông tin admin
                            </h2>
                            <!--CTA -->

                        <c:import url="include/titleHello.jsp"/>


                        <!-- General elements -->
                        <h4
                            class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"
                            >
                            Thông Admin
                        </h4>
                        <form action="ControllerAdmin" method="Post">
                            <input type="hidden" name="form" value="update"/>
                            <input type="hidden" name="id" value="${admin.idAdmin}" />
                            <div
                                class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800"
                                >
                                <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">Tên admin</span>
                                    <input
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="Nhập tên"
                                        name="fullname"
                                        value="<c:out value="${admin.fullName}"/>"
                                        type="text"
                                        />
                                    <%
                                        if (request.getAttribute("errorFullname") != null && request.getAttribute("errorFullname").toString().equals("errorfullname")) {
                                            out.print("<p class='text-sm font-medium text-red-600'>Vui lòng không nhập số và các kí tự đặc biệt</p>");
                                        }
                                    %>
                                </label>

                                <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">Ngày sinh</span>
                                    <input
                                        type="date"
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="Ngày sinh"
                                        name="birthday"
                                        value="<c:out value="${admin.birthday}"/>"
                                        />
                                    <%
                                        if (request.getAttribute("errorDate") != null && request.getAttribute("errorDate").toString().equals("errorDate")) {
                                            out.print("<p class='text-sm font-medium text-red-600'> Lỗi cập nhật vui lòng tải lại trang</p>");
                                        }
                                    %>
                                </label>
                                <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">số điện thoại</span>
                                    <input
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="số điện thoại"
                                        name="numberphone"
                                        value="<c:out value="${admin.sdt}"/>"
                                        type="number"
                                        />
                                    <%
                                        if (request.getAttribute("errorNumberPhone") != null && request.getAttribute("errorNumberPhone").toString().equals("errornumberphone")) {
                                            out.print("<p class='text-sm font-medium text-red-600'> Lỗi cập nhật vui lòng tải lại trang</p>");
                                        }
                                    %>
                                </label>
                                <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">Gmail</span>
                                    <input
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="Gmail"
                                        name="gmail"
                                        value="<c:out value="${admin.gmail}"/>"
                                        type="gmail"
                                        />
                                    <%
                                        if (request.getAttribute("errorGmail") != null && request.getAttribute("errorGmail").toString().equals("errorgmail")) {
                                            out.print("<p class='text-sm font-medium text-red-600'> Sai cú pháp gmail</p>");
                                        }
                                    %>
                                </label>
                                <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">Nhập mật khẩu</span>
                                    <input
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="mật khẩu cũ"
                                        name="oldpassword"
                                        type="password"
                                        />
                                    <%
                                        if (request.getAttribute("errorPassword") != null && request.getAttribute("errorPassword").toString().equals("errorpassword")) {
                                            out.print("<p class='text-sm font-medium text-red-600'> Mật khẩu sai</p>");
                                        }
                                    %>
                                </label>
                                <label class="block text-sm">
                                    <span class="text-gray-700 dark:text-gray-400">Mật khẩu mới</span>
                                    <input
                                        class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                                        placeholder="mật khẩu mới"
                                        name="newpassword"
                                        type="password"
                                        />
                                    <%
                                        if (request.getAttribute("errorNewPassword") != null && request.getAttribute("errorNewPassword").toString().equals("errornewpassword")) {
                                            out.print("<p class='text-sm font-medium text-red-600'> Mật khẩu không đúng yêu cầu</p>");
                                        }
                                    %>
                                </label>
                                <button  type="submit" class="flex items-center justify-between p-2 mb-5 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple" style="margin-top: 20px">Cập nhật</button>
                            </div>              
                        </form>
                    </div>
                </main>
            </div>
        </div>
    </body>
    <script>
        $('.datepicker').datepicker()
    </script>
</html>
