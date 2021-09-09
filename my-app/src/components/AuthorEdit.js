import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavBar from '../AppNavBar';

class AuthorEdit extends Component {

    emptyItem = {
        firstName: '',
        lastName: '',
        patronymic: '',
        biography: '',
        yearOfBirth: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const author = await (await fetch(`/library/author/${this.props.match.params.id}`)).json();
            this.setState({item: author});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        await fetch('/library/author' + (item.id ? '/' + item.id : ''), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/library/author');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Author' : 'Add Author'}</h2>;

        return <div>
            <AppNavBar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>

                    <FormGroup>
                        <Label for="firstName">First name</Label>
                        <Input type="text" name="firstName" id="firstName" value={item.firstName}
                               onChange={this.handleChange} autoComplete="firstName"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="lastName">Last name</Label>
                        < Input type="text" name="lastName" id="lastName" value={item.lastName}
                                onChange={this.handleChange} autoComplete="lastName"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="patronymic">patronymic</Label>
                        <Input type="text" name="patronymic" id="patronymic" value={item.patronymic}
                               onChange={this.handleChange} autoComplete="patronymic"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="biography">biography</Label>
                        <Input type="text" name="biography" id="biography" value={item.biography}
                               onChange={this.handleChange} autoComplete="biography"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="yearOfBirth">yearOfBirth</Label>
                        <Input type="text" name="yearOfBirth" id="yearOfBirth" value={item.yearOfBirth}
                               onChange={this.handleChange} autoComplete="yearOfBirth"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/library/user">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(AuthorEdit);