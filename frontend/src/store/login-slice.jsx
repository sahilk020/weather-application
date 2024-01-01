import { createSlice } from "@reduxjs/toolkit";

const loginSlice = createSlice({
  name: "login",
  initialState: {
    isLoggedIn: Boolean(localStorage.getItem("authReponse")),
    authResponse: localStorage.getItem("authResponse"),
  },
  reducers: {
    login(state, action) {
      state.isLoggedIn = action.payload.isLoggedIn;
      state.authResponse = action.payload.token;
    },
    logout(state) {
      state.isLoggedIn = false;
      state.authResponse = null;
      localStorage.removeItem("authReponse");
    },
  },
});

export const actions = loginSlice.actions;
export default loginSlice;
