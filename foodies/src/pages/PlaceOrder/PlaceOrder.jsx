import React from "react";
import "./PlaceOrder.css";
import { assets } from "../../assets/assets";
const PlaceOrder = () => {
  return (
    <div className="container mt-4">
      <main>
        <div className="py-5 text-center">
          <img
            className="d-block mx-auto mb-4"
            src={assets.logo}
            alt=""
            width={98}
            height={98}
          />
        </div>
        <div className="row g-5">
          <div className="col-md-5 col-lg-4 order-md-last">
            <h4 className="d-flex justify-content-between align-items-center mb-3">
              <span className="text-primary">Your cart</span>

              <span className="badge bg-primary rounded-pill">3</span>
            </h4>

            <ul className="list-group mb-3">
              <li className="list-group-item d-flex justify-content-between lh-sm">
                <div>
                  <h6 className="my-0">Product name</h6>

                  <small className="text-body-secondary">
                    Brief description
                  </small>
                </div>

                <span className="text-body-secondary">$12</span>
              </li>

              <li className="list-group-item d-flex justify-content-between lh-sm">
                <div>
                  <h6 className="my-0">Second product</h6>

                  <small className="text-body-secondary">
                    Brief description
                  </small>
                </div>

                <span className="text-body-secondary">$8</span>
              </li>

              <li className="list-group-item d-flex justify-content-between lh-sm">
                <div>
                  <h6 className="my-0">Third item</h6>

                  <small className="text-body-secondary">
                    Brief description
                  </small>
                </div>

                <span className="text-body-secondary">&#x24;5</span>
              </li>

              <li className="list-group-item d-flex justify-content-between">
                <span>Total (USD)</span> <strong>&#x24;20</strong>
              </li>
            </ul>
          </div>

          <div className="col-md-7 col-lg-8">
            <h4 className="mb-3">Billing address</h4>

            <form className="needs-validation" novalidate>
              <div className="row g-3">
                <div className="col-sm-6">
                  <label htmlFor="firstName" className="form-label">
                    First name
                  </label>

                  <input
                    type="text"
                    className="form-control"
                    id="firstName"
                    placeholder=""
                    value=""
                    required
                  />
                </div>

                <div className="col-sm-6">
                  <label htmlFor="lastName" className="form-label">
                    Last name
                  </label>

                  <input
                    type="text"
                    className="form-control"
                    id="lastName"
                    placeholder=""
                    value=""
                    required
                  />
                </div>

                <div className="col-12">
                  <label htmlFor="email" className="form-label">
                    Email
                  </label>

                  <div className="input-group has-validation">
                    <span className="input-group-text">@</span>

                    <input
                      type="text"
                      className="form-control"
                      id="email"
                      placeholder="Email"
                      required
                    />
                  </div>
                </div>

                <div className="col-12">
                  <label htmlFor="phone" className="form-label">
                    Phone Number
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    id="phone"
                    placeholder="123-456-7890"
                    required
                  />
                </div>

                <div className="col-md-5">
                  <label htmlFor="country" className="form-label">
                    Country
                  </label>

                  <select className="form-select" id="country" required>
                    <option value="">Choose...</option>

                    <option>United States</option>
                  </select>
                </div>

                <div className="col-md-4">
                  <label htmlFor="state" className="form-label">
                    State
                  </label>

                  <select className="form-select" id="state" required>
                    <option value="">Choose...</option>

                    <option>California</option>
                  </select>
                </div>

                <div className="col-md-3">
                  <label htmlFor="zip" className="form-label">
                    Zip
                  </label>

                  <input
                    type="number"
                    className="form-control"
                    id="zip"
                    placeholder="98745"
                    required
                  />

                  <div className="invalid-feedback">Zip code required.</div>
                </div>
              </div>
              <hr className="my-4" />
              <button className="w-100 btn btn-primary btn-lg" type="submit">
                Continue to checkout
              </button>
            </form>
          </div>
        </div>
      </main>
    </div>
  );
};

export default PlaceOrder;
