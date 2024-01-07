import { Container, CssBaseline, Typography } from '@mui/material'
import React from 'react'

export const WeatherMain = ({mainInfo}) => {
  const imageUrl = `https://openweathermap.org/img/wn/${mainInfo.weather[0].icon}.png`
  return (
    <Container component='div' sx={{display:'flex'}} maxWidth='lg'>
      <CssBaseline/>
      <Container component='div' sx={{width:20}}><img src={imageUrl} alt="weather icon" /></Container>
      <Container component='div'>
      <Typography component='body' variant='body1'>{mainInfo.dt_txt}</Typography>
      <Typography component='body' variant='body2'>{mainInfo.weather[0].description}</Typography>
      <Typography component='body' variant='body2'>Feels Like {mainInfo.main.feels_like}</Typography>
      </Container>
      <Container component='div'>
      <Typography component='body' variant='body2'>Ground Level {mainInfo.main.grnd_level}</Typography>
      <Typography component='body' variant='body2'>Humidity {mainInfo.main.humidity}</Typography>
      <Typography component='body' variant='body2'>Pressure {mainInfo.main.pressure}</Typography>
      </Container>
      <Container>
      <Typography component='body' variant='body2'>Sea Level {mainInfo.main.sea_level}</Typography>
      <Typography component='body' variant='body2'>Max {mainInfo.main.temp_max}</Typography> 
      <Typography component='body' variant='body2'>Min {mainInfo.main.temp_min}</Typography>
      </Container>
    </Container>
  )
}
