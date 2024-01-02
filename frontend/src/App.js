import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import Header from "./components/header/Header";
import Home from "./components/Home/Home";
import { Suspense, lazy } from "react";
import Footer from "./components/Footer/Footer";

const Login = lazy(()=>import('./components/Login/Login'));
const Register = lazy(()=>import('./components/Register/Register'));
function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/register" element={<Suspense fallback={<div>Loading...</div>}><Register /></Suspense>} />
        <Route path="/login" element={<Suspense fallback={<div>Loading...</div>}><Login /></Suspense>} />
        <Route path="/" element={<Home/>}/>
      </Routes>
      <Footer/>
    </BrowserRouter>
  );
}

export default App;
