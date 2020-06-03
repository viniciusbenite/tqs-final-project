import React, { Component } from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import CssBaseline from '@material-ui/core/CssBaseline';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import NavbarDono from "../components/NavbarDono";
import api from "../services/api";

const useStyles = makeStyles((theme) => ({
  icon: {
    marginRight: theme.spacing(2),
  },
  heroContent: {
    backgroundColor: theme.palette.background.paper,
    padding: theme.spacing(8, 0, 6),
  },
  heroButtons: {
    marginTop: theme.spacing(4),
  },
  cardGrid: {
    paddingTop: theme.spacing(8),
    paddingBottom: theme.spacing(8),
  },
  card: {
    height: '100%',
    
    display: 'flex',
    opacity: 1,
    flexDirection: 'column',
  },
  cardDone: {
    height: '100%',
    
    display: 'flex',
    opacity: 0.4,
    flexDirection: 'column',
  },
  cardMedia: {
    paddingTop: '0%', // 16:9
  },
  cardContent: {
    flexGrow: 1,
  },
  footer: {
    
    backgroundColor: theme.palette.background.paper,
    padding: theme.spacing(6),
  },
}));

const cards = [1, 2, 3, 4];
const isDone=[true,false,false,false];
const classes = useStyles;

export default class Album extends Component{

  state={
    reservas: [],
  };

  componentDidMount() {
    this.GetReservations();
  }
  GetReservations() {
    api.get("/reservation").then(res => {
      let dataRes = res.data;
      for (const n in dataRes) {
        if(dataRes[n].services.saloon.owner.email == localStorage.getItem("user_email")){
          this.setState(prevState =>({
            reservas: [...prevState.reservas, dataRes[n]]
          }))
        }
      }

    });
  }

  render() {

  return (
    <React.Fragment>
         <NavbarDono></NavbarDono>
      <CssBaseline />

      <main>
     
        {/* Hero unit */}
        <div className={classes.heroContent}>
          <Container maxWidth="sm">
            <Typography component="h5" variant="h4" align="center" color="textPrimary" gutterBottom>
            </Typography>
            <Typography component="h1" variant="h4" align="center" color="textPrimary" gutterBottom>
             A reservas efetuadas nos meus salões:
            </Typography>
            <div className={classes.heroButtons}>
              
            </div>
          </Container>
        </div>
        <Container className={classes.cardGrid} maxWidth="md">
          {/* End hero unit */}
          <Grid container spacing={4}>
            {this.state.reservas.map((e,key) => (
              <Grid item key={key} xs={12} sm={6} md={3}>
              
                <Card className= {isDone[cards.indexOf(key)]?classes.cardDone:classes.card}>
                 
                  <CardMedia
                  component="img"
                    className={classes.cardMedia}
                    
                    src={require('../images/cabeleireiro1.jpg')}
                    title=""
                    
                    
                  />
                  
                  <CardContent className={classes.cardContent}>
                    <Typography gutterBottom variant="h6" component="h6">
                      {e.service.saloon.name} 
                    </Typography>
                    <Typography>
                        Data: {e.date}
                    </Typography>
                    <Typography>
                        Hora: {e.time}
                    </Typography>
                    <Typography>
                     Serviço: {e.service.name}
                    </Typography>
                    <Typography>
                      Cliente: {e.users.name}
                    </Typography>
                  
                  </CardContent>
                 
                    
               
                </Card>
              </Grid>
            ))}
          </Grid>
        </Container>
      </main>
      
    </React.Fragment>
  )};
}
