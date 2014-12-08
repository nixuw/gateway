$(function () {

    $.extend($.fn, {serializeJSON: function () {
        var value = {};
        $.each($(this).serializeArray(), function () {
            value[this.name] = this.value;
        })
        return value;
    }});

    $("select[name=type]").change(function () {
        if ($(this).val() == "2") {
            $(this).parents("li").next().show();
        } else {
            $(this).parents("li").next().hide();
        }
    });
    $("button:contains('获取流水号')").click(function () {
        var _this = this;
        $.getJSON("http://localhost:8080/OCC_GATEWAY_Web/service/ajax.htm?action=F101", $(this).parents("form").serializeJSON(), function ($result) {
            $("input[name=sign]").val($result.sign);
            $.post("http://localhost:8080/OCC_GATEWAY_Web/service/access_serial.htm", $(_this).parents("form").serializeJSON(), function ($result) {
                if ($result.isInvokeSuccess == "SUCCESS") {
                    $(_this).next().show();
                    $("input[name=sign]").val($result.sign);
                    $("input[name=serialNum]").val($result.serialNum);
                } else {
                    $(_this).next().hide();
                    alert($result.errorMessage);
                }
            }, "json");
        })
    });
});