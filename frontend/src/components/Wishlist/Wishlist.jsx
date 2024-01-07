import {
  Button,
  Card,
  CardActionArea,
  CardActions,
  CardContent,
  Container,
  Typography,
  Snackbar,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import weather from "../../axios-create";
import DeleteIcon from "@mui/icons-material/Delete";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

export default function Wishlist() {
  const [open, setOpen] = useState(false);
  const [list, setList] = useState([]);
  const token = useSelector((state) => state.token);
  const nav = useNavigate();
  const handleDelete = (item) => {
    weather
      .delete("/wishlist/delete", {
        headers: { Authorization: token },
        data: item,
      })
      .then((res) => {
        console.log(res);
        setOpen(true);
      })
      .catch((err) => console.error(err));
  };
  useEffect(() => {
    weather
      .get("/wishlist/get", { headers: { Authorization: token } })
      .then((res) => {
        console.log(res.data);
        setList(res.data);
      })
      .catch((err) => console.error(err));
  }, [open, token]);
  return (
    <Container component="main" sx={{ pt: 5, pb: 5, display: "flex",justifyContent:'center'}}>
      {list &&
        list.map((wishlist) => (
          <Card
            onClick={()=>nav("/weather", {
              state: { lat: wishlist.lat, lon: wishlist.lon },
            })}
            key={`${wishlist.lat} ${wishlist.lon} ${wishlist.username}`}
            sx={{ maxWidth: 345, mr: 2 }}
          >
            <CardActionArea>
              <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                  {`${wishlist.city},${wishlist.country}`}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  (`${wishlist.lat},${wishlist.lon}`)
                </Typography>
              </CardContent>
            </CardActionArea>
            <CardActions>
              <Button size="small" onClick={() => handleDelete(wishlist)}>
                <DeleteIcon sx={{ color: "red" }} />
              </Button>
            </CardActions>
          </Card>
        ))}
      <Snackbar
        open={open}
        autoHideDuration={1000}
        onClose={() => setOpen(false)}
        message="Item Deleted"
      />
      {list.length === 0 && (
        <Typography component="h4" variant="h4">
          Can't Find Any Items in Wishlist
        </Typography>
      )}
    </Container>
  );
}
// @NotBlank
// 	private String city;
// 	@NotBlank
// 	private String country;
// 	@NotBlank
// 	private double lat;
// 	@NotBlank
// 	private double lon;
// 	@NotBlank
// 	private String username;
