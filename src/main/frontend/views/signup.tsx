import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useForm } from '@vaadin/hilla-react-form';
import { useEffect } from 'react';
import { FormLayout, TextField, Button } from '@vaadin/react-components';
import { PasswordField } from '@vaadin/react-components/PasswordField.js';

import { UserRegistrationService } from 'Frontend/generated/endpoints';
import UserRegistrationDto from 'Frontend/generated/com/example/application/usermanagement/dto/UserRegistrationDto';
import UserRegistrationDtoModel from 'Frontend/generated/com/example/application/usermanagement/dto/UserRegistrationDtoModel';
import handleError from 'Frontend/views/_ErrorHandler';

export const config: ViewConfig = {
  title: 'Signup',
};

export default function SignupView() {
  const { model, submit, field, addValidator } = useForm(UserRegistrationDtoModel, {
    onSubmit: async (registration) => {
      try {
        await UserRegistrationService.registerUser(registration);
      } catch (error) {
        handleError(error);
      }
    },
  });

  const responsiveSteps = [{ minWidth: '0', columns: 1 }];

  useEffect(() => {
    addValidator({
      message: 'Please check that the password is repeated correctly',
      validate: (value: UserRegistrationDto) => {
        if (value.password != value.passwordConfirmation) {
          return [{ property: model.passwordConfirmation }];
        }
        return [];
      },
    });
  }, []);

  return (
    <main className="bobby-signup-view w-full h-full flex flex-col box-border gap-s p-m">
      <div className="bobby-signup-form">
        <h2> Sign up </h2>
        <FormLayout responsiveSteps={responsiveSteps}>
          <TextField label="Username" {...field(model.username)} />
          <TextField label="Email" {...field(model.email)} />
          <PasswordField label="Password" {...field(model.password)} />
          <PasswordField label="Confirm Password" {...field(model.passwordConfirmation)} />
        </FormLayout>
        <Button theme="primary" onClick={submit}>
          Register
        </Button>
      </div>
    </main>
  );
}
