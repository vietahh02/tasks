<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="icon" href="img/logo.png" type="image/gif" />
        <title>Job</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/font-awesome/css/all.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link
            rel="stylesheet"
            type="text/css"
            href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"
            />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="home">

            <c:if test="${sessionScope.acc == null}">
                Empty...
            </c:if>

            <c:if test="${sessionScope.acc != null}">
                <div style="float: right">
                        <select id="sort" name="sort" style="padding: 5px;background-color: #28a745;color: white">
                            <option value="1">Date &uparrow;</option>
                            <option value="2" ${sort == '2' ? 'selected' : ''}>Date &downarrow;</option>
                            <option value="3" ${sort == '3' ? 'selected' : ''}>Prioritize &uparrow;</option>
                            <option value="4" ${sort == '4' ? 'selected' : ''}>Prioritize &downarrow;</option>
                        </select>

                    </div>
                
                <div style="text-align: center; padding-bottom: 10px">
                    <button class="new" style="border-radius: 5px;color: white;padding: 5px 10px;background-color: #007bff">Add New</button>
                </div>
                <c:forEach items="${tasks}" var="ts">

                    <div style="text-align: center; padding: 10px;color: palevioletred;font-weight: 600;font-size: 20px">
                        ${ts[0].getTaskDate()}
                    </div>

                    <div class="tableAd">
                        <table>
                            <tr>
                                <th style="width: 20%">Name</th>
                                <th style="width: 25%">time</th>
                                <th >Prioritize</th>
                                <th ></th>
                            </tr>

                            <c:forEach items="${ts}" var="item">
                                <tr>
                                    <td>${item.getTaskName()}</td>
                                    <td>${item.getTaskTime()}</td>
                                    <td>
                                        <script>
                                            starAll = "${item.getPrioritize()}";
                                            i = 0;
                                            for (; i < Math.floor(starAll); i++) {
                                                document.writeln("<i class=\"fa-solid fa-star\" style=\"color: #ffc808;\"></i>");
                                            }
                                            if (Math.floor(starAll) - starAll < 0) {
                                                document.writeln("<i class=\"fa-solid fa-star-half-stroke\" style=\"color: #ffc808;\"></i>");
                                                i++;
                                            }
                                            for (; i < 5; i++) {
                                                document.writeln("<i class=\"fa-regular fa-star\" style=\"color: #ffc808;\"></i>");
                                            }
                                        </script>

                                    </td>
                                    <td>
                                        <button class="remove"num="${item.getTaskID()}">Remove</button>
                                        <button class="detail" num="${item.getTaskID()}">Detail</button>
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>
                </c:forEach>
            </c:if>

        </div>
        <div id="toast"></div>
        <script src="./js/toast.js"></script>
        <script>
                                            $("#sort").change(function () {
                                                window.location.href = "home?sort=" + $(this).val();
                                            });
                                            suc = "${suc}";
                                            err = "${err}";
                                            if (suc !== "null" && suc !== "") {
                                                success(suc);
                                            }
                                            if (err !== "null" && err !== "") {
                                                error(err);
                                            }
                                            console.log(suc, err);

                                            $(".new").click(function () {
                                                window.location.href = "new";
                                            });
                                            $(".remove").click(function () {
                                                id = $(this).attr("num");
                                                if (confirm("You want delete task id=" + id + "?")) {
                                                    window.location.href = "update?mode=1&id=" + id;
                                                }
                                            });
                                            $(".detail").click(function () {
                                                id = $(this).attr("num");
                                                window.location.href = "update?mode=2&id=" + id;
                                            });

        </script>

        <jsp:include page="footer.jsp"/>

        <script
            type="text/javascript"
            src="https://code.jquery.com/jquery-1.11.0.min.js"
        ></script>
        <script
            type="text/javascript"
            src="https://code.jquery.com/jquery-migrate-1.2.1.min.js"
        ></script>
        <script
            type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"
        ></script>
    </body>
</html>