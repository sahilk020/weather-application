import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/header/Header";
import Home from "./components/Home/Home";
import { Suspense, lazy } from "react";
import Footer from "./components/Footer/Footer";
import Loading from "./components/Loading/Loading";
import SearchCity from "./components/SearchCity/SearchCity";
import Weather from "./components/Weather/Weather";
import { ProtectedRoute } from "./ProtectedRoute/ProtectedRoute";

const Login = lazy(() => import("./components/Login/Login"));
const Register = lazy(() => import("./components/Register/Register"));
const Wishlist = lazy(() => import("./components/Wishlist/Wishlist"));
function App() {
  return (
    <BrowserRouter>
      <Header />
      <Suspense fallback={<Loading />}>
        <Routes>
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route
            path="/wishlist"
            element={
              <ProtectedRoute>
                <Wishlist />
              </ProtectedRoute>
            }
          />
          <Route
            path="/searchCity"
            element={
              <ProtectedRoute>
                <SearchCity />
              </ProtectedRoute>
            }
          />
          <Route path="/" element={<Home />} />
          <Route
            path="/weather"
            element={
              <ProtectedRoute>
                <Weather />
              </ProtectedRoute>
            }
          />
        </Routes>
      </Suspense>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
