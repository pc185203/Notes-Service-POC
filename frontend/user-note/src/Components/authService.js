import {authUser} from "./ApiMethods";

export const login = async(u, p) => {
    if (!u || !p) {
        throw new Error("* Both fields are required");
    }
    const cred = {username: u, password: p}
    const response = await authUser(cred);
    if (response.status === 400) {
        throw new Error(response.data);
    }
    sessionStorage.setItem("token", btoa(`${u}:${p}`));
};