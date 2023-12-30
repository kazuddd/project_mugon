<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" href="profile.css">
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<style>
    .profil-form {
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

    .profile-container {
        margin: auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin-top: 50px;
        width: 700px;
        height: 500px;
    }

    .profileAd-container {
        margin: auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin-top: 50px;
        width: 700px;
        height: 270px;
    }

    .biodata {
        display: flex;
        gap : 16px;
    }

    .gambar{
        height: 200px;
        display: flex;
        gap:10px;
        flex-direction: column;
        background-color: rgb(194, 189, 189);
        border: 1px solid black;
    }

    .ContainerHistori {
        background-color: rgb(201, 201, 201);
        height: 45%;
        width: 100%;
        justify-content: center;
    }


</style>

<body class="h-[100vh] bg-gradient-to-b from-[#3F0071] to-[#150050]">
<div class="Menu">
    <div class="logo">
        <a href="/menuadm"><img src="../asep/Group 6.png" alt="Logo Mugon" class="mug-1"/></a>
    </div>
    <div class="profile">
        <img src="../asep/profile.png" alt="Profil Anda">
        <span1>Admin Boli</span1>
    </div>
    <div class="logout">
        <a id="logout" href="/loginadmin">Logout</a>
    </div>
</div>


<div class="profilAd-form">
    <img src="../asep/Group 12.png" alt="Gambar Anda" class="mug-2">
    <div class="profileAd-container">
        <div class="biodata">
            <div class="gambar">
                <div>
                    <img src="../asep/Buhahri.jpg" alt="Buhahri.jpg">
                </div>
                <button class="bg-purple-900 text-white rounded-[30px] mt-1">Pilih Foto</button>
            </div>
            <div>
                <button>Biodata diri</button>
                <p>Nama : Admin Boli</p>
                <p>Id : 1301212323</p>
                <p>Alamat : Jalan-Jalan Bandung</p>
                <p>Email : Bolioceannananana@gmail.com</p>
            </div>
        </div>
</body>
</html>