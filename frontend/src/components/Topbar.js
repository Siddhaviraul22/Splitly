import React from "react";
import { useNavigate } from "react-router-dom";

function Topbar() {
    const navigate = useNavigate();

    const logout = () => {
        localStorage.removeItem("token");
        navigate("/login");
    };

    return (
        <div style={styles.topbar}>
            <button onClick={logout} style={styles.btn}>Logout</button>
        </div>
    );
}

const styles = {
    topbar: {
        display: "flex",
        justifyContent: "flex-end",
        padding: "15px"
    },
    btn: {
        padding: "8px 15px",
        border: "none",
        background: "#0db9a7",
        color: "white",
        borderRadius: "5px",
        cursor: "pointer"
    }
};

export default Topbar;
