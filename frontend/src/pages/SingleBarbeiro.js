import React, { Component } from 'react';
import defaultBcg from '../images/esta.jpg';
import Banner from '../components/Banner';
import { Link } from 'react-router-dom';
import { CabeleireirosContext } from '../context';
import StyledHero from '../components/StyledHero';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import FormControl from "@material-ui/core/FormControl";
import RadioGroup from "@material-ui/core/RadioGroup";
import Radio from "@material-ui/core/Radio";
import NavbarCliente from "../components/NavbarCliente";
import NavbarDono from "../components/NavbarDono";
import api from "../services/api";


export default class SingleBarbeiro extends Component {

    componentDidMount() {
        this.GetServices();
    }

    GetServices (){
        api.get("/service/").then(res => {
            this.setState({
                servicos: res.data,

            })
            let names =  [];
            let tempDic = {};
            let data = res.data;
            for (const i in data){
                if(data[i].saloon.id == parseInt(this.props.match.params.slug)){
                    tempDic["value"] = data[i].name;
                    tempDic["label"] = data[i].name;
                    names.push(tempDic);
                    tempDic = {};
                }
            }
            this.setState({
                servicosName: names,
            })
        });
    }


    handleChangeDate = date => {
        this.setState({
            startDate: date
        });
        this.setState({
            isActive: true
        })
    };

    handleChangeService(event) {
        this.setState({servico: event.target.value});
        let x = event.target.value;
        api.get("/schedule/").then(rSch =>{
            let dataSch = rSch.data;
            let hor =  [];
            let tempDic = {};
            for (const n in dataSch) {
                if(dataSch[n].service.saloon.id == parseInt(this.props.match.params.slug)){
                    if(x == dataSch[n].service.name){
                        this.setState({
                            serv: dataSch[n].service.id,
                        })
                        tempDic["value"] = dataSch[n].startTime;
                        tempDic["label"] = dataSch[n].startTime;
                        hor.push(tempDic);
                        tempDic = {};
                    }
                }
            }
            this.setState({
                horarios: hor,
            })

        })
    }

    handleSubmit = async event => {
        const { serv, hour, startDate,slug } = this.state;
        event.preventDefault();

        let dateFinal = startDate.toString().split(" ");
        dateFinal = dateFinal[2] +" "+ dateFinal[1] +" "+ dateFinal[3];

        try {
            console.log(serv)
            console.log(dateFinal)
            console.log(hour);
            console.log(slug);

            await api.get("/user/").then(function (response) {
                let data = response.data;
                let res;
                for (const i in data) {
                    for (const n in data[i]) {
                        if (data[i].email === localStorage.getItem("user_email")) {
                            res = data[i];
                        }
                    }
                }
                api.get("/service/").then(resSer => {
                    let dataresSer = resSer.data;
                    for (const n in dataresSer) {
                        if (dataresSer[n].id == parseInt(serv)) {
                            console.log(dataresSer[n])
                            api.post("/reservation/", {
                                date: dateFinal,
                                time: hour,
                                users: res,
                                services: dataresSer[n]
                            }).then();
                        }
                    }
                })
            })


            alert("Reserva efetuada!")
            await api.get("/schedule/",).then(rHor =>{
                let datarHor = rHor.data;
                for (const n in datarHor) {
                    if(datarHor[n].startTime == hour){
                        if(datarHor[n].service.id == parseInt(serv)){
                            if(datarHor[n].service.id == parseInt(serv)){
                                console.log(datarHor[n].id)
                            }
                        }
                    }
                }

            })
            this.props.history.push("/home");

        } catch (err) {
            console.log(err);
        }

    }

    constructor(props) {
        super(props);
        this.state = {
            slug: parseInt(this.props.match.params.slug),
            defaultBcg,
            servico: "",
            hour: "",
            isActive: false,
            servicos: [],
            servicosName:[],
            horarios: [],
            serv:"",
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChangeService = this.handleChangeService.bind(this);
    }

    static contextType = CabeleireirosContext;

    // componentDidMount() {}
    render() {
        const { getBarbeiro } = this.context;
        const barbeiro = getBarbeiro(this.state.slug);

        if (!barbeiro) {
            return <div className="error">
                <h3>Este cabeleireiro não existe</h3>
                <Link to='/cabeleireiros' className="btn-primary">back to cabeleireiros</Link>
            </div>
        }
        const {nome, cidade, descricao, imagens,morada,contacto} = barbeiro;
        //this.setState({cabeleireiroName: nome});
        const [mainImg,...defaultImg] = imagens;
        return(
            <>
                {localStorage.getItem("user_type")=="dono" ? <NavbarDono></NavbarDono>:<NavbarCliente></NavbarCliente> }
                <StyledHero img={mainImg ||
                this.state.defaultBcg}>
                    <Banner title={`${nome}`} subtitle='Faça já a sua marcação!'>
                        <Link to="/barbeiros" className="btn-primary">
                            voltar
                        </Link>
                    </Banner>
                </StyledHero>

                <section className="single-cabeleireiro">
                    <div className="single-cabeleireiro-info">
                        <article className="desc">
                            <h3>detalhes</h3>
                            <p>{descricao}</p>
                            <p>Contacto Telefónico: {contacto}</p>
                            <p>{morada}</p>
                            <p>{cidade}</p>
                        </article>
                        <article className="info">
                        </article>
                    </div>
                </section>
                <section className="cabeleireiro-extras">
                    <h3>Serviços disponíveis</h3>

                </section>
                <div className="service_option">
                    <form onSubmit={this.handleSubmit}>
                        <label>
                            Escolha o Serviço:
                            <select name="servico" value={this.state.servico} onChange={this.handleChangeService}>
                                {this.state.servicosName.map((e, key) => {
                                    return <option key={key} value={e.value}>{e.label}</option>;
                                })}
                            </select>
                        </label>
                        <label>
                            Escolha um Dia:
                            <DatePicker className="calendar"
                                        selected={this.state.startDate}
                                        onChange={this.handleChangeDate}
                                        minDate={new Date()}
                                        withPortal

                            />
                        </label>
                        <div>
                            {this.state.isActive ?
                                <FormControl component="fieldset">
                                    <label>Escolha uma HOra:
                                        <RadioGroup aria-label="time" name="hour" value={this.state.hour}
                                                    onChange={e => this.setState({ hour: e.target.value })}>
                                            {this.state.horarios.map((e, key) => {
                                                return <FormControlLabel key={key} value={e.value}  control={<Radio/>} label={e.label}/>;
                                            })}
                                        </RadioGroup>
                                    </label>
                                </FormControl>
                                : null}
                        </div>
                        <div className="reservar">
                        <input type="submit" value="Reservar" />
                        </div>
                    </form>
                </div>
                <section className="cabeleireiro-extras">
                </section>
            </>
        );
    }
}
