import React, { Component } from 'react';
import defaultBcg from '../images/cabeleireiro1.jpg';
import Banner from '../components/Banner';
import { Link } from 'react-router-dom';
import { CabeleireirosContext } from '../context';
import StyledHero from '../components/StyledHero';
import makeStyles from "@material-ui/core/styles/makeStyles";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import FormControl from "@material-ui/core/FormControl";
import FormLabel from "@material-ui/core/FormLabel";
import RadioGroup from "@material-ui/core/RadioGroup";
import Radio from "@material-ui/core/Radio";

const useStyles = makeStyles((theme) => ({
    button: {
        display: 'block',
        marginTop: theme.spacing(2),
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
    },
}));

export default class SingleCabeleireiro extends Component {
    state = {
        isActive:false
    }

    handleChangeService(event) {
        this.setState({value: event.target.value});
    }
    handleChangeDate = date => {
        this.setState({
            startDate: date
        });
        this.setState({
            isActive: true
        })
    };
    handleSubmit(event) {
        alert('Reservation done! (' + this.state.value + ')');
        event.preventDefault();
    }
    handleChangeHour(event) {
        this.setState({
            value: event.target.value
        });
        alert('Hour: ' + event.target.value);
    }
    constructor(props) {
        super(props);
    //console.log(this.props)
        this.state = {
            slug: this.props.match.params.slug,
            defaultBcg
        };
        this.handleChangeService = this.handleChangeService.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChangeHour = this.handleChangeHour.bind(this);
    }

    static contextType = CabeleireirosContext;
   // componentDidMount() {}
    render() {
        const { getCabeleireiro } = this.context;
        const cabeleireiro = getCabeleireiro(this.state.slug);
        if (!cabeleireiro) {
            return <div className="error">
                <h3>Este cabeleireiro não existe</h3>
                <Link to='/cabeleireiros' className="btn-primary">back to cabeleireiros</Link>
                </div>
        }
        const {nome,
          cidade,
              descricao,

              imagens} = cabeleireiro;
        const [mainImg,...defaultImg] = imagens;
        return(
        <>
        <StyledHero img={mainImg ||
          this.state.defaultBcg}>
            <Banner title={`${nome}`} subtitle='Faça já a sua marcação!'>
                <Link to="/cabeleireiros" className="btn-primary">
                    voltar
            </Link>
            </Banner>
        </StyledHero>

            <section className="single-cabeleireiro">
                <div className="single-cabeleireiro-info">
                <article className="desc">
                <h3>detalhes</h3>
                <p>{descricao}</p>
                </article>
                    <article className="info">
                    </article>
                </div>
          </section>
        <section className="cabeleireiro-extras">
        <h6>Serviços disponíveis</h6>
        <h7>Aqui deve aparecer uma lista de serviços (corte masculino, coloração,...) com preços +- talvez, e
            que dê para clicar, abrir um calendário com dias disponiveis a partir do de hoje,
            escolher a data e hora da reserva </h7>

        </section>
        <div className="service_option">
            <form onSubmit={this.handleSubmit}>
                <label>
                    Pick your Service:
                    <select value={this.state.value} onChange={this.handleChangeService}>
                        <option value="cortes">Cortes - 5.90€</option>
                        <option value="manicure_pedicure">Manicure e Pedicure - 7.99€</option>
                        <option value="escovas">Escovas - 5.90€</option>
                        <option value="tratamento_capilar">Tratamento Capilar - 7.99€</option>
                        <option value="serviços_especiais">Serviços Especiais - 10.90€</option>
                        <option value="depilacao">Depilação - 8.49€</option>
                        <option value="maquilhagem">Maquilhagem - 3.99€</option>
                        <option value="quimicas">Químicas Em Geral - 11.99€</option>
                    </select>
                </label>
                <label>
                    Choose an available day:
                    <DatePicker className="calendar"
                                selected={this.state.startDate}
                                onChange={this.handleChangeDate}
                    />
                </label>
                <div>
                    {this.state.isActive ?
                        <FormControl component="fieldset">
                            <label>Select a available hour:
                                <RadioGroup aria-label="gender" name="gender1" value={this.state.value}
                                            onChange={this.handleChangeHour}>
                                    <FormControlLabel value="hour1" control={<Radio/>} label="10h - 11h"/>
                                    <FormControlLabel value="hour2" control={<Radio/>} label="14h - 15h30m"/>
                                    <FormControlLabel value="hour3" control={<Radio/>} label="16h30m - 17h30m"/>
                                </RadioGroup>
                            </label>
                        </FormControl>
                        : null}
                </div>
                <input type="submit" value="Submit" />
            </form>
        </div>
        <section className="cabeleireiro-extras">
        </section>
        </>
      );
    }
}
