import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavBar from '../AppNavBar';

class BookAuthorRemove extends Component {

    emptyItem = {
        firstName: '',
        lastName: '',
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
        const book = await (await fetch(`/library/book/${this.props.match.params.id}`)).json();
        this.setState({item: book});
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

        await fetch('/library/bookAuthor/remove/' + item.id, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/library/book');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{'Remove Author Book'}</h2>;

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
                        <Label for="yearOfBirth">yearOfBirth</Label>
                        <Input type="text" name="yearOfBirth" id="yearOfBirth" value={item.yearOfBirth}
                               onChange={this.handleChange} autoComplete="yearOfBirth"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">OK</Button>{' '}
                        <Button color="secondary" tag={Link} to="/library/book">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(BookAuthorRemove);