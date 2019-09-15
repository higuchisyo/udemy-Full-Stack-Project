import React from "react";
import { Redirect, Route } from "react-router-dom";
import { connect } from "react-redux";
import PropTypes from "prop-types";

const SecureRoute = ({ conmponent: Component, security, ...otherProps }) => (
  <Route
    {...otherProps}
    render={props =>
      security.validToken === true ? (
        <Component {...props} />
      ) : (
        <Redirect to="/login" />
      )
    }
  />
);

SecureRoute.propTypes = {
  security: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  security: state.security
});

export default connect(mapStateToProps)(SecureRoute);
