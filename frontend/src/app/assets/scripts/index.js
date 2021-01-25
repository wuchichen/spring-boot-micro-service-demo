import "../styles/style.css";

var customers = [];
var orders = [];

/**
 *
 * @param {String} customerName name of customer
 * @returns {Promise<Object>} result
 */
function createCustomer(customerName) {
  return new Promise((resolve, reject) => {
    $.ajax({
      method: "POST",
      url: "/api/customers/create",
      data: JSON.stringify({
        name: customerName,
      }),
      dataType: "json",
      contentType: "application/json",
    }).done(function (data) {
      resolve(data);
    });
  });
}

/**
 *
 * @param {String} description description of order
 * @param {Number} customerId customer id
 * @returns {Promise<Object>} result
 */
function createOrder(description, customerId) {
  return new Promise((resolve, reject) => {
    $.ajax({
      method: "POST",
      url: "/api/orders/create",
      data: JSON.stringify({
        description: description,
        customerId: customerId,
      }),
      dataType: "json",
      contentType: "application/json",
    }).done(function (data) {
      resolve(data);
    });
  });
}

function onConfirmAddCustomerBtnClick() {
  let customerName = $("#fc-customer-name").val();

  if (customerName) {
    createCustomer(customerName)
      .then((data) => {
        customers.push(data);

        // Update table
        let tr$ = $("<tr></tr>");
        tr$.append(`<td>${data.id}</td>`);
        tr$.append(`<td>${data.name}</td>`);
        $("#customers-table-body").append(tr$);

        // Update dropdown
        let option$ = $(`<option value="${data.id}">${data.name}</option>`);
        $("#fc-order-customers-dropdown").append(option$);

        // Close modal
        $("#customerModal").modal("hide");
      })
      .catch((err) => console.log(err));
  } else {
    throw "Invalid Customer!";
  }
}

function onConfirmAddOrderBtnClick() {
  let orderDescription = $("#fc-order-description").val();
  let customerId = $("#fc-order-customers-dropdown").val();

  if (orderDescription && (!!customerId || customerId == 0)) {
    createOrder(orderDescription, parseInt(customerId))
      .then((data) => {
        orders.push(data);

        // Update table
        let tr$ = $("<tr></tr>");
        tr$.append(`<td>${data.id}</td>`);
        tr$.append(`<td>${data.description}</td>`);
        tr$.append(`<td>${data.customerId}</td>`);
        $("#order-table-body").append(tr$);

        // Close modal
        $("#orderModal").modal("hide");
      })
      .catch((err) => console.log(err));
  } else {
    throw "Invalid Order!";
  }
}

function main() {
  $("#add-customer-btn-confirm").click(onConfirmAddCustomerBtnClick);

  $("#add-order-btn-confirm").click(onConfirmAddOrderBtnClick);
}

main();
