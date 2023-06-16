<%@ page import="cdp.fms.model.BillBook" %>
<%@ page import="cdp.fms.dao.BillBookDao" %>
<%@ page import="cdp.fms.dao.Impl.BillBookDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.model.User" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>财务分析</title>
    <script src="jquery/jquery.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/main.css">
    <style>


        #top-title {
            background-color: #F0AD4E;
            margin-left: 10px;
            margin-right: 10px;
            margin-top: 10px;
            margin-bottom: 10px;
            text-align: center;
        }


        #top-title-font-m {
            font-size: 20px;
            color: white;
            margin-left: auto;
            margin-right: auto;
        }


        #body-left {
            width: 500px;
            height: 400px;
            margin: auto;
        }


        #l-font {
            font-size: 20px;
            margin-left: 10px;
            margin-right: 10px;
        }

        #anlycisis {
            width: 100%;
            height: auto;
            font-size: 20px;
            color: red;
            float: left;
            margin-top: 5px;
            margin-left: 50px;
        }
    </style>
</head>
<body>
<%@include file="commons/header.jsp" %>
<%
    User auth = (User) request.getSession().getAttribute("user");
    List<BillBook> billBookList1 = null;
    List<BillBook> billBookList2 = null;
    List<BillBook> billBookList3 = null;
    List<BillBook> billBookList4 = null;

    if (auth != null) {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int year = today.getYear();
        request.setAttribute("user", auth);
        BillBookDao billBookDao = new BillBookDaoImpl();
        billBookList1 = billBookDao.getMonthIncome(month, auth.getId());
        billBookList2 = billBookDao.getMonthOutcome(month, auth.getId());
        billBookList3 = billBookDao.getYearIncome(year, auth.getId());
        billBookList4 = billBookDao.getMonthOutcome(year, auth.getId());


    } else {
        response.sendRedirect("main.jsp");
    }

    if (billBookList1 != null && billBookList2 != null && billBookList3 != null && billBookList4 != null) {
        float a = 0, b = 0, c = 0, d = 0;
        int m = 0, n = 0;
        for (BillBook o : billBookList1) {
            //本月收入
            a = o.getMoney() + a;
            m++;
        }
        for (BillBook o1 : billBookList2) {
            //本月支出
            b = o1.getMoney() + b;
            n++;
        }
        for (BillBook o2 : billBookList4) {
            //本年收入
            c = o2.getMoney() + c;
        }
        for (BillBook o3 : billBookList3) {
            //本年支出
            d = o3.getMoney() + d;
        }

%>

<div id="body-left">
    <div id="top-title">
        <lable id="top-title-font-m">本月财务状况</lable>
    </div>
    <div id="l-font">
        <div style="font-size:20px;">
            本月总收支：<span id="currentAllMoney"><%=a%></span> 元 -- 收入占<span
                id="currentIncomePercent"><%=a / (a + b)*100%>%</span>， 支出占<span
                id="currentSpendPercent"><%=b / (a + b)*100%>%</span>
        </div>
        <!-- 画表，作为左边部分 -->
        <div id="container-left"
             style="width: 350px; height: 200px;float: left;margin-left: 75px;margin-right: 75px;"></div>
        <script language="JavaScript">
            $(document).ready(function () {
                var chart = {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                };
                var title = {
                    text: ''
                };
                var tooltip = {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                };
                var plotOptions = {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}%</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                };
                var series = [{
                    type: 'pie',
                    name: '本月收支',
                    data: [
                        ['支出', <%=b%>],
                        ['收入', <%=a%>]
                    ]
                }];

                var json = {};
                json.chart = chart;
                json.title = title;
                json.tooltip = tooltip;
                json.series = series;
                json.plotOptions = plotOptions;
                $('#container-left').highcharts(json);
            });
        </script>
        <div style="font-size:20px;">
            收入记录：<span id="currentIncomeRecord"><%=m%></span>条 ----收入总额 <span
                id="currentIncomeMoney"><%=a%></span>元
        </div>
        <div style="font-size:20px;">
            支出记录：<span id="currentSpendRecord"><%=n%></span>条 ----支出总额 <span
                id="currentSpendMoney"><%=b%></span>元
        </div>

    </div>
</div>

<div id="body-left">
    <div id="top-title">
        <lable id="top-title-font-m">上月同期收入对比</lable>
    </div>
    <div id="l-font">
        <div style="font-size:10px;text-align: center;">
            收入情况
        </div>
        <br>
        <div id="container-tleft"
             style="width: 350px; height: 200px;float: left;margin-left: 75px;margin-right: 75px;"></div>
        <script language="JavaScript">
            $(document).ready(function () {
                var chart = {
                    type: 'bar'
                };
                var title = {
                    text: ''
                };
                var subtitle = {
                    text: ''
                };
                var xAxis = {
                    categories: ['收入', '支出'],
                    title: {
                        text: null
                    }
                };
                var yAxis = {
                    min: 0,
                    title: {
                        text: '元',
                        align: 'high'
                    },
                    labels: {
                        overflow: 'justify'
                    }
                };
                var tooltip = {
                    valueSuffix: 'yuan'
                };
                var plotOptions = {
                    bar: {
                        dataLabels: {
                            enabled: true
                        }
                    }
                };
                var legend = {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'top',
                    x: -40,
                    y: 100,
                    floating: true,
                    borderWidth: 1,
                    backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
                    shadow: true
                };
                var credits = {
                    enabled: false
                };

                var series = [{
                    name: '本月',
                    data: [<%=a%>, <%=b%>]
                }, {
                    name: '本年',
                    data: [<%=c%>, <%=d%>]
                }
                ];

                var json = {};
                json.chart = chart;
                json.title = title;
                json.subtitle = subtitle;
                json.tooltip = tooltip;
                json.xAxis = xAxis;
                json.yAxis = yAxis;
                json.series = series;
                json.plotOptions = plotOptions;
                json.legend = legend;
                json.credits = credits;
                $('#container-tleft').highcharts(json);

            });
        </script>
    </div>
</div>
<%
    }
%>
<%@include file="commons/footer.jsp" %>

<script src="js/bootstrap.min.js"></script>
</body>

</html>