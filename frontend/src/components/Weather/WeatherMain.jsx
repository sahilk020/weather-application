import { Container, Typography } from '@mui/material'
import React from 'react'

export const WeatherMain = ({mainInfo}) => {
  return (
    <Container component='div'>
      <Typography component='body' variant='body1'>{mainInfo.dt_txt}</Typography>
      <Typography component='body' variant='body2'>Feels Like {`${mainInfo.main.feels_like} ${mainInfo.weather[0].description}`}</Typography>
      <Typography component='body' variant='body2'>Ground Level {mainInfo.main.grnd_level}</Typography>
      <Typography component='body' variant='body2'>Humidity {mainInfo.main.humidity}</Typography>
      <Typography component='body' variant='body2'>Pressure {mainInfo.main.pressure}</Typography>
      <Typography component='body' variant='body2'>Sea Level {mainInfo.main.sea_level}</Typography>
      <Typography component='body' variant='body2'>Max Temp {mainInfo.main.temp_max}</Typography> 
      <Typography component='body' variant='body2'>Min Temp {mainInfo.main.temp_min}</Typography> 
    </Container>
  )
}
