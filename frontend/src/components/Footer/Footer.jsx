import { Container, Link, Typography } from "@mui/material";
import React from "react";
import styles from "./Footer.module.css";
export default function Footer() {

  return (
    <Container component='footer' maxWidth='xl' className={styles.container}>
        <ul className={styles.links}>
          <li>About</li>
          <li>WeatherApp</li>
          <li>Links</li>
        </ul>
        <Copyright />
    </Container>
  );
}
function Copyright(props) {
  return (
    <Typography variant="body2" color="white" align="center" {...props}>
      {"Copyright © "}
      <Link color="inherit" href="https://mui.com/">
        Weather App
      </Link>{" "}
      {new Date().getFullYear()}
    </Typography>
  );
}
