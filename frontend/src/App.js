import { BrowserRouter, Route, Routes } from "react-router-dom";
// import "./App.css";
import Header from "./components/header/Header";
import Home from "./components/Home/Home";
import { Suspense, lazy } from "react";
import Footer from "./components/Footer/Footer";
import Loading from "./components/Loading/Loading";
import SearchCity from "./components/SearchCity/SearchCity";

const Login = lazy(() => import("./components/Login/Login"));
const Register = lazy(() => import("./components/Register/Register"));
const Wishlist = lazy(()=> import('./components/Wishlist/Wishlist'))
function App() {
  return (
    <BrowserRouter>
      <Header />
      <Suspense fallback={<Loading />}>
        <Routes>
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/wishlist" element={<Wishlist/>}/>
          <Route path="/searchCity" element={<SearchCity/>}/>
          <Route path="/" element={<Home />} />
        </Routes>
      </Suspense>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
