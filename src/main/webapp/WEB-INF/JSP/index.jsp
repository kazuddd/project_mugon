<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MUGON Login Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}src/main/webapp/WEB-INF/JSP/index.jsp">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300&display=swap">
</head>

<style>

    @import url('https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300&display=swap');

    a{
        text-decoration: none;
    }
    body {
        font-family: Arial, sans-serif;
        background: linear-gradient(to bottom, #3F0071, #150050);
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    .login-container {
        background-color: #fff;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        max-width: 400px;
        margin-left: 100px;
        width: 400px;
        height: 500px;
    }

    .login-container h2 {
        text-align: center;
        margin-bottom: 10px;
    }

    .login-form input[type="text"],
    .login-form input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .login-form button {
        width: 100%;
        padding: 10px;
        background-color: #6200ff;
        color: #fff;
        border: none;
        border-radius: 10px;
        cursor: pointer;
    }

    .login-form button:hover {
        background-color: #a200ff;
    }

    hr {
        border: none;
        height: 30px;
        background: transparent;
        margin: 0;
        padding: 0;
    }


    .mug-1 {
        width: 217px;
        height: 97px;
        position: absolute; /* Mengatur posisi absolut */
        top: 10%; /* Menggeser gambar ke tengah vertikal */
        transform: translateY(-50%); /* Menyesuaikan posisi gambar ke tengah vertikal */
    }

    .mug-2 {
        width: 1032px; /* Lebar gambar */
        height: 614px; /* Tinggi gambar */
    }

    .menu {
        display: flex;
        justify-content: space-between;
        font-family: 'Josefin Sans', sans-serif;
        font-weight: bolder;
    }

    .menu2 {
        display: flex;
        justify-content: space-between;
        font-family: 'Josefin Sans', sans-serif;
        font-weight: bolder;
    }

    .loginSubmit {
        color: #fff;
    }

    .menu a {
        font-size: 30px; /* Ganti ukuran font sesuai keinginan Anda */
    }

    .login-form label[for="username"] {
        margin-bottom: 10px; /* Ganti angka sesuai dengan jarak yang diinginkan */
    }



    .login-form button[type= "submit"]{
        font-size: 20px;
        font-weight: bold;
        margin-top: 20px;
    }
</style>
<body>

<img src="../../asep/Group%206.png" alt="Mugon gede" class="mug-1">
<img src="../../asep/Group%2012.png" alt="Gambar Anda" class="mug-2">

<div class="login-container">
    <div class="menu">
        <a style="color: silver;" href="/daftar">Daftar</a>
        <a style="color: black;" href="#">Login</a></div>
    <hr>
    <hr>
    <div class="menu2">
        <a style="color: black;" href="#">User</a>
        <a style="color: silver;" href="/logininspektor">Inspektor</a>
        <a style="color: silver;" href="/loginadmin">Admin</a>
    </div>
    <hr>

    <form class="login-form" action="/login" method="post">
        <label for="email"></label>
        <input type="text" id="email" name="email" required placeholder="Email">

        <input type="text" id="password" name="password" required placeholder="Password">

        <button type="submit">
            <a style="color: #fff" >BERIKUTNYA</a>
        </button>
    </form>

    <script>
        function loginForm() {
            var username = document.getElementById("email").value;
            var password = document.getElementById("password").value;

            // Client-side validation
            if (!username || !password) {
                alert("Please enter both email and password.");
                return;
            }

            // Simulate a successful login - Replace this with your actual login logic
        }
    </script>
</div>
</body>
</html>
