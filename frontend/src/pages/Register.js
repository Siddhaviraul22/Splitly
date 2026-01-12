import { useState } from "react";
import axios from "axios";

function Register() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleRegister = async (e) => {
        e.preventDefault();

        await axios.post("http://localhost:8080/auth/register", {
            name,
            email,
            password,
        });

        window.location.href = "/login";
    };

    return (
        <div style={styles.container}>
            <form style={styles.card} onSubmit={handleRegister}>
                <h2>Create Account</h2>

                <input
                    placeholder="Name"
                    onChange={(e) => setName(e.target.value)}
                    style={styles.input}
                />

                <input
                    placeholder="Email"
                    onChange={(e) => setEmail(e.target.value)}
                    style={styles.input}
                />

                <input
                    type="password"
                    placeholder="Password"
                    onChange={(e) => setPassword(e.target.value)}
                    style={styles.input}
                />

                <button style={styles.button}>Register</button>
            </form>
        </div>
    );
}

const styles = {
    container: {
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        background: "#14b8a6",
    },
    card: {
        background: "white",
        padding: "30px",
        borderRadius: "8px",
        width: "300px",
    },
    input: {
        width: "100%",
        padding: "10px",
        marginTop: "10px",
    },
    button: {
        width: "100%",
        marginTop: "15px",
        padding: "10px",
        background: "#0f766e",
        color: "white",
        border: "none",
    },
};

export default Register;
