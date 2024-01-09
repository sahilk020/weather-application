import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import "@testing-library/jest-dom/extend-expect";
import Login from "../components/Login/Login";
import weather from "../axios-create";
import configureStore from "redux-mock-store";
import { Provider } from "react-redux";
import { MemoryRouter } from "react-router-dom";
jest.mock("../store/login-slice");
const mockStore = configureStore([]);
jest.mock("../axios-create");

describe("Login component", () => {
  let store;
  beforeEach(() => {
    store = mockStore({
      isLoggedIn: false,
      token: "",
      username: "",
    });
    let data = { token: "testToken", username: "testUser" };
    weather.post.mockImplementationOnce(() => Promise.resolve(data));
  });

  test("renders login form", () => {
    render(
      <Provider store={store}>
        <MemoryRouter>
          <Login />
        </MemoryRouter>
      </Provider>
    );
    expect(screen.getByLabelText(/Username/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/Password/i)).toBeInTheDocument();
    expect(screen.getByRole("button", { name: /Login/i })).toBeInTheDocument();
  });

  test("submits form with valid input", async () => {
    render(
      <Provider store={store}>
        <MemoryRouter>
          <Login />
        </MemoryRouter>
      </Provider>
    );
    const usernameInput = screen.getByLabelText(/Username/i);
    const passwordInput = screen.getByLabelText(/Password/i);
    const loginButton = screen.getByRole("button", { name: /Login/i });

    userEvent.type(usernameInput, "testuser");
    userEvent.type(passwordInput, "testpassword");
    fireEvent.click(loginButton);

    await waitFor(() =>expect(weather.post).toHaveBeenCalled());
  });

  test("displays validation error for empty fields", async () => {
    render(
      <Provider store={store}>
        <MemoryRouter>
          <Login />
        </MemoryRouter>
      </Provider>
    );
    const loginButton = screen.getByRole("button", { name: /Login/i });

    fireEvent.click(loginButton);

    // Assuming your validation messages are displayed using the "helperText" prop
    await waitFor(()=>{
        expect(screen.getByText(/Please enter your username/i)).toBeInTheDocument();
    })
    await waitFor(()=>{expect(screen.getByText(/Please enter your password/i)).toBeInTheDocument();})

    // You can add more assertions based on your validation schema
  });

  // Add more test cases based on your specific requirements and edge cases
});
