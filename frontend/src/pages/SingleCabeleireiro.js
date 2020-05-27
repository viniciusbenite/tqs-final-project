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
import NavbarCliente from "../components/NavbarCliente";
import NavbarDono from "../components/NavbarDono";
import api from "../services/api";


export default class SingleCabeleireiro extends Component {

    handleChangeDate = date => {
        this.setState({
            startDate: date
        });
        this.setState({
            isActive: true
        })
    };
    handleSubmit = async event => {
        const { servico, hour, startDate } = this.state;
        event.preventDefault();

        try {
            console.log(servico)
            console.log(startDate)
            console.log(hour)
            let d = new Date("01:15:00");
            let dat = Date.parse(startDate);
            d.setMinutes(56);
            let user = localStorage;

            let serv = await api.post("/service/",{name: servico});
            let s = serv.data;
            console.log(serv);
            await api.post("/reservation/",{date: startDate,time:hour, service: servico} );
            alert("Reservation done!")
            this.props.history.push("/cabeleireiros/salao");
          } catch (err) {
            console.log(err);
          }
    }
    
    constructor(props) {
        super(props);
        //console.log(defaultBcg)
        this.state = {
            slug: this.props.match.params.slug,
            defaultBcg,
            servico: "",
            hour: "",
            cabeleireiro:"",
            isActive: false
        };
        this.handleSubmit = this.handleSubmit.bind(this);
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
        {localStorage.getItem("user_type")=="dono" ? <NavbarDono></NavbarDono>:<NavbarCliente></NavbarCliente> }
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

        </section>
        <div className="service_option">
            <form onSubmit={this.handleSubmit}>
                <label>
                    Pick your Service:
                    <select name="servico" value={this.state.servico} onChange={e => this.setState({ servico: e.target.value })}>
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
                                <RadioGroup aria-label="time" name="hour" value={this.state.hour}
                                            onChange={e => this.setState({ hour: e.target.value })}>
                                    <FormControlLabel value="09:00:00" control={<Radio/>} label="09:00:00"/>
                                    <FormControlLabel value="17:30:00" control={<Radio/>} label="17:30:00"/>
                                    <FormControlLabel value="14:50:00" control={<Radio/>} label="14:50:00"/>
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
