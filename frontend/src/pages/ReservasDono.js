import React from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import CssBaseline from '@material-ui/core/CssBaseline';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import Link from '@material-ui/core/Link';
import NavbarDono from "../components/NavbarDono";

function ReservasDono() {
  
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Reservas © '}
      <Link color="inherit" href="https://material-ui.com/">
        Your Website
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

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
const isDone=[true,false,false,false]

export default function Album() {
  const classes = useStyles();
  
  return (
    <React.Fragment>
         <NavbarDono></NavbarDono>
      <CssBaseline />
     
      <main>
     
        {/* Hero unit */}
        <div className={classes.heroContent}>
          <Container maxWidth="sm">
            <Typography component="h5" variant="h4" align="center" color="textPrimary" gutterBottom>
             As reservas efetuadas para o meu salão:
            </Typography>
            <div className={classes.heroButtons}>
              
            </div>
          </Container>
        </div>
        <Container className={classes.cardGrid} maxWidth="md">
          {/* End hero unit */}
          <Grid container spacing={4}>
            {cards.map((card) => (
              <Grid item key={card} xs={12} sm={6} md={4}>
              
                <Card className= {isDone[cards.indexOf(card)]?classes.cardDone:classes.card}>
                 
                  <CardMedia
                  component="img"
                    className={classes.cardMedia}
                    
                    src={require('../images/cabeleireiro1.jpg')}
                    title=""
                    
                    
                  />
                  
                  <CardContent className={classes.cardContent}>
                    <Typography gutterBottom variant="h5" component="h2">
                      Cabeleireiro 1 - Lisboa
                    </Typography>
                    <Typography>
                      25 de Abril de 2020
                    </Typography>
                    <Typography>
                      13h - 14h  
                    </Typography>
                    <Typography>
                     Serviço: Corte de cabelo
                    </Typography>
                  
                  </CardContent>
                 
                    
               
                </Card>
              </Grid>
            ))}
          </Grid>
        </Container>
      </main>
      
    </React.Fragment>
  );
}
