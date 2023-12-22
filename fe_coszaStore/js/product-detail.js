$(document).ready(function(){
    // Gọi hàm lấy danh sách dữ liệu khi trang được tải
    getAllSize();
    getAllColor();
});

function getAllSize() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8090/size",
        headers: {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZ3V5ZW52YW5iQGdtYWlsLmNvbSJ9.T248SzY1J-CcnzGsoYehDI8n4acYl3wvl-S8dxyyW6M"
        },
        success: function(result){
            console.log(result);
            var str = "";
            $.each(result.data, function (index, data){
                    str += '<option>';
                    str += data.name;
                    str += '</option>';
            });
            $('#load_size').html(str);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Có lỗi xảy ra khi lấy dữ liệu: " + textStatus + ", " + errorThrown);
        }
    })
} 

function getAllColor() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8090/color",
        headers: {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZ3V5ZW52YW5iQGdtYWlsLmNvbSJ9.T248SzY1J-CcnzGsoYehDI8n4acYl3wvl-S8dxyyW6M"
        },
        success: function(result){
            console.log(result);
            var str = "";
            $.each(result.data, function (index, data){
                str += '<option>';
                str += data.name;
                str += '</option>';
            });
            $('#load_color').html(str);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Có lỗi xảy ra khi lấy dữ liệu: " + textStatus + ", " + errorThrown);
        }
    })
} 

function getDetailProduct() {

}