function addToCart(productId) {
    fetch('/cart/add', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'productId=' + productId
    })
        .then(response => response.text())
        .then(data => alert(data)) // Покажет уведомление "Product added to cart"
        .catch(error => console.error('Error:', error));
}
