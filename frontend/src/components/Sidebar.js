import React from "react";
import { Link } from "react-router-dom";

function Sidebar() {
    return (
        <div style={styles.sidebar}>
            <h2 style={styles.logo}>Splitly</h2>

            <Link to="/dashboard" style={styles.link}>Dashboard</Link>
            <Link to="/groups" style={styles.link}>Groups</Link>
            <Link to="/expenses" style={styles.link}>Expenses</Link>
        </div>
    );
}

const styles = {
    sidebar: {
        width: "220px",
        background: "linear-gradient(180deg,#0db9a7,#059b8c)",
        color: "white",
        minHeight: "100vh",
        padding: "20px"
    },
    logo: {
        marginBottom: "40px"
    },
    link: {
        display: "block",
        color: "white",
        textDecoration: "none",
        marginBottom: "15px"
    }
};

export default Sidebar;
