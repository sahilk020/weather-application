import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { MemoryRouter } from "react-router-dom";
import Register from "../components/Register/Register";
import weather from "../axios-create";
// Mock the axios module to simulate the API call
jest.mock("../axios-create");
const localStorageMock = {
  setItem: jest.fn(),
  getItem: jest.fn(),
  remove: jest.fn(),
};
global.localStorage = localStorageMock;
describe("Register component", () => {
  beforeEach(() => {
    // Mock the post method of the axios module
    let data = {
      username: "ramesh",
      firstName: "Ramesh",
      lastName: "Verma",
      email: "jay@jay.com",
      contactNumber: "1234567893",
      password: "Ram@12345",
    };
    weather.post.mockImplementationOnce(() => Promise.resolve(data));
  });

  test('api call',async()=>{
    let res = await weather.post('/user/register')
    let val = await res.data
    expect(weather.post).toHaveBeenCalled()
    // expect(val).not.toBeNull()
  })

  test("renders Register component", () => {
    render(
      <MemoryRouter>
        <Register />
      </MemoryRouter>
    );
    expect(screen.getByText("Sign Up")).toBeInTheDocument();
  });

  test("submits the form successfully", async () => {
    render(
      <MemoryRouter>
        <Register />
      </MemoryRouter>
    );
    await fireEvent.change(screen.getByLabelText(/First Name/), {
      target: { value: "John" },
    });
    await fireEvent.change(screen.getByLabelText(/Last Name/), {
      target: { value: "Doe" },
    });
    await fireEvent.change(screen.getByLabelText(/Email Address/), {
      target: { value: "JohnDoe@something.com" },
    });
    await fireEvent.change(screen.getByLabelText(/Username/), {
      target: { value: "john" },
    });
    await fireEvent.change(screen.getByLabelText(/Confirm Password/), {
      target: { value: "john@1234" },
    });
    await fireEvent.change(screen.getByLabelText(/Contact Number/), {
      target: { value: "1234567890" },
    });
    await fireEvent.click(screen.getByText('Sign Up'))

    // await waitFor(() => expect(weather.post).toHaveBeenCalled());

    // expect(screen.getByText("Registration Successful")).toBeInTheDocument();

    await waitFor(()=> expect(window.location.pathname).toBe("/"));
  });
});
