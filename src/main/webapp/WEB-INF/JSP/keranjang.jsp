<%@ page import="com.example.project_mugon.Model.Barang" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" href="Keranjang.css">
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<style>

    @import url('https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300&display=swap');

    body {
        font-family: 'Josefin Sans', sans-serif;
        margin: 0;
        padding: 0;
        font-weight: bold;
    }

    .Keranjang-form {
        display: flex;
        justify-content: space-between;
        flex-direction: row-reverse;
    }

    .profilAd-form {
        display: flex;
        justify-content: space-between;
        flex-direction: row-reverse;

    }

    /* bar menu */

    .Menu {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px 20px;
        background: linear-gradient(to bottom, #3F0071, #150050);
        color: #fff;
        position: relative; /* Membuat posisi relative untuk elemen ini */

    }

    .search-bar{
        width: 800px;
        height: 40px;
        border-radius: 50px;
        position: absolute;
        top: 50%; /* Meletakkan di tengah vertikal */
        left: 50%; /* Meletakkan di tengah horizontal */
        transform: translate(-50%, -50%);
        z-index: 1;
    }

    .cart{
        position: absolute;
        top: 40%;
        left: 72%;
        z-index: 2;
    }

    .cart img {
        width: 30px;
    }

    .search-bar input {
        width: 800px;
        height: 35px;
        border: 5px solid white;
        border-radius: 50px;
        max-width: 800px;
    }

    .profile {
        display: flex;
        flex-direction: column;
        align-items: flex-end;
        margin-left: auto;
    }

    .profile img {
        width: 50px;
        margin-bottom: 5px;
        position: absolute;
        top: 30%;
        left: 76%;
        z-index: 3;
    }

    .logout a {
        color: #fff;
        text-decoration: none;
        margin-right: 50px;
        font-size: 20px;
        font-family: 'Josefin Sans', sans-serif;
    }

    .profile span1 {
        width: 200px;
        margin-bottom: 50px;
        position: absolute;
        top: 30%;
        left: 80%;
        z-index: 4;
        font-size: 20px;
        font-family: 'Josefin Sans', sans-serif;
        color: white;
    }
    .profile span2 {
        width: 200px;
        margin-bottom: 50px;
        position: absolute;
        top: 50%;
        left: 80%;
        z-index: 4;
        font-size: 20px;
        font-family: 'Josefin Sans', sans-serif;
        color: white;
    }

    /*  container untuk top up */

    .Keranjang-container {
        margin: auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin-top: 50px;
        width: 700px;
        height: fit-content;
    }

    .labelInspek {
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #3F0071;
        height: 80px; /* Adjust the height as needed */
        width: 40%; /* Set the width as needed */
        margin: 0 auto; /* Center horizontally */
        border-radius: 20px;
        color: white;
    }

    .labelInspek h1 {
        color: #ffffff;
        font-size: 24px;
        font-weight: bold;
    }

    .iklan {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
    }

    .iklan-box {
        width: 70%;
        border: 5px solid #bbb9b9;
        padding: 10px;
        text-align: left;
        background-color: white;
        margin-top: 5%;
        margin-left: 10%;
        box-shadow: 1px 7px 10px 4px grey;
        border-radius: 10px;
    }

    .Checkout {
        display: flex;
        flex-direction: column; /* Change to column layout */
        align-items: flex-end; /* Align items to the right */
        color: white;
    }

    .Checkout button {
        border-radius: 8px;
        background-color: #3F0071;
        padding: 10px 20px;
        margin-top: 10px; /* Add margin to separate from the total */
    }

    .total {
        display: flex;
        color: white;
        border-radius: 8px;
        background-color: #3F0071;
        width: 200px;
        justify-content: center;
        align-items: center;
        margin-bottom: 10px; /* Add margin at the bottom */



</style>

<body class="h-[100vh] bg-gradient-to-b from-[#3F0071] to-[#150050]">
<div class="Menu">
    <div class="logo">
        <a href="/menu"><img src="../asep/Group 6.png" alt="Logo Mugon" class="mug-1"/></a>
    </div>
    <div class="profile">
        <a href="/profile"> <img src="../asep/profile.png" alt="Profil Anda"/></a>
        <a href="/topup"><span1>${loggedInUser.nama}</span1></a>
        <a href="/topup"><span2>Rp. ${loggedInUser.nama}</span2></a>
    </div>
    <div class="search-bar">
        <input type="text" placeholder="Cari produk...">
    </div>
    <div class="cart">
        <a href="/keranjang"><img src="../asep/keranjang.png" alt="Keranjang Belanja"/></a>
    </div>

    <div class="logout">
        <a id="logout" href="/">Logout</a>
    </div>
</div>



<div class="Keranjang-form">
    <img src="../asep/Group 12.png" alt="Gambar Anda" class="mug-2">
    <div class="Keranjang-container">
        <div class="labelInspek">
            <h1>KERANJANG KU</h1>
        </div>

        <!-- Looping untuk setiap item dalam list -->
        <%
            List<Barang> items = (List<Barang>) session.getAttribute("barangKeranjang");
            if (items != null) {
                for (int i = 0; i < items.size(); i++) {
                    Barang item = items.get(i);
        %>

        <div class="iklan-box">
            <h3><%= item.getNamaBarang() %></h3>
            <p>ID: <%= item.getID() %></p>
            <p>Harga: <%= item.getHarga() %></p>
            <p>Rating: <%= item.getKondisi() %></p>
            <p>Lokasi: <%= item.getLokasi() %></p>
        </div>

        <!-- Akhir dari looping -->
        <%
                }
            }
        %>

        <form action="/Checkout" method="post">
            <div class="Checkout">
                <button type="submit">CheckOut</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>