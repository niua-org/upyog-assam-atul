import React from "react";
import { Loader } from "@upyog/digit-ui-react-components";

const EPramaanLogoutLoader = () => {
  return (
    <div
      style={{
        position: "fixed",
        top: 0,
        left: 0,
        width: "100vw",
        height: "100vh",
        background: "white",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        zIndex: 999999,
      }}
    >
      <div style={{ textAlign: "center" }}>
        <div
          style={{
            fontSize: "18px",
            color: "#333",
            marginBottom: "20px",
            fontWeight: "500",
          }}
        >
          Logging out...
        </div>
        <Loader />
      </div>
    </div>
  );
};

export default EPramaanLogoutLoader;