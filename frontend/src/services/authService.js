import API from "./api";

export const login = async (email, password) => {
    const response = await API.post("/auth/login", {
        email,
        password,
    });

    return response.data;
};
