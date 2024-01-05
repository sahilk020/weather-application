import { createSlice } from "@reduxjs/toolkit";

const loginSlice = createSlice({
  name: "login",
  initialState: {
    isLoggedIn: Boolean(localStorage.getItem("token")),
    token: localStorage.getItem("token"),
    username: localStorage.getItem("username"),
  },
  reducers: {
    login(state, action) {
      state.isLoggedIn = true;
      state.token = action.payload.token;
      state.username = action.payload.username;
    },
    logout(state) {
      state.isLoggedIn = false;
      state.token = null;
      localStorage.removeItem("token");
      localStorage.removeItem("username");
    },
  },
});

export const actions = loginSlice.actions;
export default loginSlice;
