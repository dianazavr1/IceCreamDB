document.addEventListener("DOMContentLoaded", loadCart);

function loadCart() {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    let cartList = document.getElementById("cart-items");
    let totalPrice = 0;

    cartList.innerHTML = "";

    cart.forEach((item, index) => {
        let li = document.createElement("li");
        li.textContent = `${item.name} - ${item.price} сом`;

        let removeBtn = document.createElement("button");
        removeBtn.textContent = "✖";
        removeBtn.onclick = () => removeFromCart(index);

        li.appendChild(removeBtn);
        cartList.appendChild(li);

        totalPrice += item.price;
    });

    document.getElementById("total-price").textContent = totalPrice;
}

function removeFromCart(index) {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    cart.splice(index, 1);
    localStorage.setItem("cart", JSON.stringify(cart));
    loadCart();
}

function clearCart() {
    localStorage.removeItem("cart");
    loadCart();
}
