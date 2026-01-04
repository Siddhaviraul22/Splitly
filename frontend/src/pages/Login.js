import { useNavigate } from "react-router-dom";

function Login() {
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        navigate("/dashboard");
    };

    return (
        <div>
            <h2>Login</h2>

            <form onSubmit={handleSubmit}>
                <div>
                    <label>Email</label><br />
                    <input type="email" />
                </div>

                <div>
                    <label>Password</label><br />
                    <input type="password" />
                </div>

                <button type="submit">Login</button>
            </form>
        </div>
    );
}

export default Login;
