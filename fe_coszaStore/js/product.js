$(document).ready(function(){
    // Gọi hàm lấy danh sách dữ liệu khi trang được tải
    getAllCategory();
    getAllProduct();
    getAllCategoryFooter();
    
});

function getAllCategory() { 
    $.ajax({
        method: "GET",
        url: "http://localhost:8090/category",
        headers: {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZ3V5ZW52YW5iQGdtYWlsLmNvbSJ9.T248SzY1J-CcnzGsoYehDI8n4acYl3wvl-S8dxyyW6M"
        },
        success: function(result){
            console.log(result);
            var str = "";
            $.each(result.data, function (index, data){
                    var getProductByCategory = "http://localhost:8090/product/category/" + data.id;
                    str += '<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" onclick="showProductByCategory(\'' + getProductByCategory + '\')">';
                    str += data.name;
                    str += '</button>';
            });
            $('#load_category').html(str);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Có lỗi xảy ra khi lấy dữ liệu: " + textStatus + ", " + errorThrown);
        }
    })
}

function showProductByCategory(getProductByCategory) {
    $.ajax({
        method: "GET",
        url: getProductByCategory,
        headers: {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZ3V5ZW52YW5iQGdtYWlsLmNvbSJ9.T248SzY1J-CcnzGsoYehDI8n4acYl3wvl-S8dxyyW6M"
        },
        success: function(result){
            console.log(result);
            var str = "";
            $.each(result.data, function (index, data){
                    var getDetailProduct = "http://localhost:8090/product/" + data.id;
                    str += '<div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item men">';
                    str += '<div class="block2">';
                    str += '<div class="block2-pic hov-img0">';
                    str += '<img src="images/' + data.image + '.jpg" alt="IMG-PRODUCT">';
                    str += '<a style="cursor:pointer" onclick = "showDetailProduct(\'' + getDetailProduct + '\')" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1" id = "' + data.id + '">';
                    str += 'Quick View';
                    str += '</a>';
                    str += '</div>';
                    str += '<div class="block2-txt flex-w flex-t p-t-14">';
                    str += '<div class="block2-txt-child1 flex-col-l ">';
                    str += '<a href="product-detail.html" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">';
                    str += data.name;
                    str += '</a>';
                    str += '<span class="stext-105 cl3">';
                    str += '$' + data.price;
                    str += '</span>';
                    str += '</div>';
                    str += '<div class="block2-txt-child2 flex-r p-t-3">';
                    str += '<a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">';
                    str += '<img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">';
                    str += '<img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">';
                    str += '</a>';
                    str += '</div>';
                    str += '</div>';
                    str += '</div>';
                    str += '</div>';
            });
            $('#load_product_by_category').html(str);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Có lỗi xảy ra khi lấy dữ liệu: " + textStatus + ", " + errorThrown);
        }
    })
}

function getAllProduct() { 
    $.ajax({
        method: "GET",
        url: "http://localhost:8090/product",
        headers: {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZ3V5ZW52YW5iQGdtYWlsLmNvbSJ9.T248SzY1J-CcnzGsoYehDI8n4acYl3wvl-S8dxyyW6M"
        },
        success: function(result){
            console.log(result);
            var str = "";
            $.each(result.data, function (index, data){
                    var getDetailProduct = "http://localhost:8090/product/" + data.id;
                    str += '<div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item men">';
                    str += '<div class="block2">';
                    str += '<div class="block2-pic hov-img0">';
                    str += '<img src="images/' + data.image + '.jpg" alt="IMG-PRODUCT">';
                    str += '<a style="cursor:pointer" onclick = "showDetailProduct(\'' + getDetailProduct + '\')" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1" id = "' + data.id + '">';
                    str += 'Quick View';
                    str += '</a>';
                    str += '</div>';
                    str += '<div class="block2-txt flex-w flex-t p-t-14">';
                    str += '<div class="block2-txt-child1 flex-col-l ">';
                    str += '<a href="product-detail.html" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">';
                    str += data.name;
                    str += '</a>';
                    str += '<span class="stext-105 cl3">';
                    str += '$' + data.price;
                    str += '</span>';
                    str += '</div>';
                    str += '<div class="block2-txt-child2 flex-r p-t-3">';
                    str += '<a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">';
                    str += '<img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">';
                    str += '<img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">';
                    str += '</a>';
                    str += '</div>';
                    str += '</div>';
                    str += '</div>';
                    str += '</div>';
            });
            $('#load_product').html(str);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Có lỗi xảy ra khi lấy dữ liệu: " + textStatus + ", " + errorThrown);
        }
    })
}

function getAllCategoryFooter() { 
    $.ajax({
        method: "GET",
        url: "http://localhost:8090/category",
        headers: {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZ3V5ZW52YW5iQGdtYWlsLmNvbSJ9.T248SzY1J-CcnzGsoYehDI8n4acYl3wvl-S8dxyyW6M"
        },
        success: function(result){
            var str = "";
            $.each(result.data, function (index, data){
                    str += '<li class="p-b-10">';
                    str += '<a href="#" class="stext-107 cl7 hov-cl1 trans-04" id="' + data.id + '">';
                    str += data.name;
                    str += '</a>';
                    str += '</li>';
            });
            $('#load_category_footer').html(str);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Có lỗi xảy ra khi lấy dữ liệu: " + textStatus + ", " + errorThrown);
        }
    })
}
function showDetailProduct(getDetailProduct) {
    $.ajax({
        method: "GET",
        url: getDetailProduct,
        headers: {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZ3V5ZW52YW5iQGdtYWlsLmNvbSJ9.T248SzY1J-CcnzGsoYehDI8n4acYl3wvl-S8dxyyW6M"
        },
        success: function(result){
            console.log(result);
            window.location.href = "product-detail.html"
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Có lỗi xảy ra khi lấy dữ liệu: " + textStatus + ", " + errorThrown);
        }
    })
}
