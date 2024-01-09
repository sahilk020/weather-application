import {
  TextField,
  Button,
  Box,
  Grid,
  Avatar,
  Typography,
  Paper,
} from "@mui/material";
import { useEffect } from "react";
import styles from "./Login.module.css";
import { LockOutlined } from "@mui/icons-material";
import { useFormik } from "formik";
import { loginSchema } from "../schema";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { actions } from "../../store/login-slice";
import weather from "../../axios-create";
//to perform login operation
function Login() {
  const isLoggedIn = useSelector((state) => state.isLoggedIn);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  let loginDetails = { username: "", password: "" };
  const { values, errors, handleChange, handleSubmit } = useFormik({
    initialValues: loginDetails,
    validationSchema: loginSchema,
    onSubmit: (value) => {
      console.log("inside", value);
      weather.post("/auth/login", value).then((res) => {
        console.log(res.data);
        localStorage.setItem('token',res.data.token)
        localStorage.setItem('username',res.data.username)
        dispatch(actions.login(res.data))
      });
    },
  });
  // console.table(values);
  useEffect(() => {
    isLoggedIn && navigate("/");
  }, [isLoggedIn, navigate]);
  return (
    <Grid container component="main" sx={{ height: "100vh" }}>
      <Grid
        item
        xs={false}
        sm={4}
        md={8}
        sx={{
          backgroundImage: "url(https://source.unsplash.com/random?wallpapers)",
          backgroundRepeat: "no-repeat",
          backgroundColor: (t) =>
            t.palette.mode === "light"
              ? t.palette.grey[50]
              : t.palette.grey[900],
          backgroundSize: "cover",
          backgroundPosition: "center",
        }}
      />
      <Grid item xs={12} sm={8} md={4} component={Paper} elevation={6} square>
        <Box className={styles.loginContainer}>
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <LockOutlined />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <form onSubmit={handleSubmit}>
            <TextField
              label="Username"
              type="text"
              name="username"
              id="username"
              fullWidth
              margin="normal"
              value={values.username}
              onChange={handleChange}
              error={Boolean(errors.username)}
              helperText={errors.username}
            />
            <TextField
              label="Password"
              type="password"
              name="password"
              id="password"
              fullWidth
              margin="normal"
              value={values.password}
              onChange={handleChange}
              error={Boolean(errors.password)}
              helperText={errors.password}
            />
            <Button
              type="submit"
              variant="contained"
              sx={{ backgroundColor: "#732d65" }}
              fullWidth
            >
              Login
            </Button>
          </form>
        </Box>
      </Grid>
    </Grid>
  );
}

export default Login;
