<%-- 
    Document   : navbarleft
    Created on : Oct 23, 2021, 4:26:31 PM
    Author     : gaone
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<aside
    class="z-20 flex-shrink-0 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block"
    >
    <div class="py-4 text-gray-500 dark:text-gray-400">
        <a
            class="ml-6 text-lg font-bold text-gray-800 dark:text-gray-200"
            href="<%= request.getContextPath()%>/ControllerPage?page=home"
            >
            Quản lý phim
        </a>
        <!--Home-->
        <ul class="mt-6">
            <li class="relative px-6 py-3">
                ${param.dashboard}
                <a
                    class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                    href="<%= request.getContextPath()%>/ControllerPage?page=home"
                    >
                    <svg
                        class="w-5 h-5"
                        aria-hidden="true"
                        fill="none"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        >
                    <path
                        d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"
                        ></path>
                    </svg>
                    <span class="ml-4">Trang chủ</span>
                </a>
            </li>
        </ul>
        <!--End Home-->

        <ul>
            <!--List Movie-->
            <li class="relative px-6 py-3">
                ${param.listmovie}
                <a
                    class="inline-flex items-center w-full text-sm font-semibold text-gray-800 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200 dark:text-gray-100"
                    href="<%= request.getContextPath()%>/ControllerPage?page=listmovie"
                    >
                    <svg
                        class="w-5 h-5"
                        aria-hidden="true"
                        fill="none"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        >
                    <path d="M4 6h16M4 10h16M4 14h16M4 18h16"></path>
                    </svg>
                    <span class="ml-4">Danh sách phim</span>
                </a>
            </li>
            <!--End List Movie-->
            
            <!--Add Movie-->
            <li class="relative px-6 py-3">
                ${param.addmovie}
                <a
                    class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                    href="<%= request.getContextPath()%>/ControllerPage?page=addmovie"
                    >
                    <svg
                        class="w-5 h-5"
                        aria-hidden="true"
                        fill="none"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        >
                    <path
                        d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01"
                        ></path>
                    </svg>
                    <span class="ml-4">Thêm phim</span>
                </a>
            </li>
                     <!--End Add Movie-->
                     
                     <!-- Add Category Movie-->
<!--            <li class="relative px-6 py-3">
                ${param.catergormovie}
                <a
                    class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                    href="<%= request.getContextPath()%>/ControllerPage?page=addmovie"
                    >
                    <svg
                        class="w-5 h-5"
                        aria-hidden="true"
                        fill="none"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        >
                    <path
                        d="M12 9v3m0 0v3m0-3h3m-3 0H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z"
                        ></path>
                    </svg>
                    <span class="ml-4">Thêm thể loại phim</span>
                </a>
            </li>-->
            <!-- End Add Category Movie-->
            
              <!-- Add Type Movie-->
<!--            <li class="relative px-6 py-3">
                ${param.typemovie}
                <a
                    class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                    href="<%= request.getContextPath()%>/ControllerPage?page=addmovie"
                    >
                    <svg
                        class="w-5 h-5"
                        aria-hidden="true"
                        fill="none"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        >
                    <path
                        d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-11a1 1 0 10-2 0v2H7a1 1 0 100 2h2v2a1 1 0 102 0v-2h2a1 1 0 100-2h-2V7z"
                        ></path>
                    </svg>
                    <span class="ml-4">Thêm loại phim</span>
                </a>
            </li>-->
                  <!-- End Type Movie-->
                  
            <!--  Information Admin-->
            <li class="relative px-6 py-3">
                ${param.profile}
                <a
                    class="inline-flex items-center w-full text-sm font-semibold text-gray-800 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200 dark:text-gray-100"
                    href="<%= request.getContextPath()%>/ControllerPage?page=profile"
                    >
                    <svg
                        class="w-5 h-5"
                        aria-hidden="true"
                        fill="none"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        >
                    <path d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
                    </svg>
                    <span class="ml-4">Thông tin admin</span>
                </a>
            </li>
              <!-- End Information Admin-->
        </ul>
        <!--          <div class="px-6 my-6">
                      <a href="<%= request.getContextPath()%>/ControllerPage?page=createaccount"
                      class="flex items-center justify-between w-full px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
                    >
                      Thêm tài khoản admin
                      <span class="ml-2" aria-hidden="true">+</span>
                    </a>
                  </div>-->
    </div>
</aside>
